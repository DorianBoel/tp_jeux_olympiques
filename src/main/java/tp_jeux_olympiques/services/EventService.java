package tp_jeux_olympiques.services;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

import jakarta.persistence.EntityManager;
import tp_jeux_olympiques.LanguageRepository;
import tp_jeux_olympiques.LineIndex;
import tp_jeux_olympiques.UndefinedEntityManagerException;
import tp_jeux_olympiques.entities.Event;
import tp_jeux_olympiques.entities.Language;
import tp_jeux_olympiques.entities.Sport;
import tp_jeux_olympiques.entities.Translation;
import tp_jeux_olympiques.enums.Distinction;

public class EventService {

	private EntityManager entityManager;
	private LanguageRepository languageRepository = LanguageRepository.getInstance();
	private SportService sportService;
	private TranslationService translationService;

	private Set<Event> events = new HashSet<>();
	
	public EventService(SportService sportService, TranslationService translationService) {
		this.sportService = sportService;
		this.translationService = translationService;
	}
	
	public Event parse(List<String> lineValues, List<String> eventLines) throws UndefinedEntityManagerException {
		String label = lineValues.get(LineIndex.EVENT.INDEX);
		String sportName = lineValues.get(LineIndex.SPORT.INDEX);
		Distinction distinction = parseDistinction(label);
		if (label.startsWith(sportName + " ")) {
			label = label.replace(sportName + " ", "");
		}
		Sport sport = sportService.findByLabel(sportName);
		Event event = create(label, languageRepository.get("en"), distinction, sport);
		String labelFR = null;
		for (String line : eventLines) {
			List<String> eventLineValues = Arrays.asList(line.split(LineIndex.SEPARATOR_SEMICOLON));
			String labelEN = eventLineValues.get(LineIndex.EVENT_LABEL_EN.INDEX);
			if (labelEN.equals(label)) {
				labelFR = eventLineValues.get(LineIndex.EVENT_LABEL_FR.INDEX);
			}
		}
		Translation translation = translationService.create(event, languageRepository.get("fr"), labelFR);
		translationService.save(translation);
		return event;
	}

	public Event create(String label, Language language, Distinction distinction, Sport sport) {
		return new Event(label, language, distinction, sport);
	}
	
	public Distinction parseDistinction(String label) {
		Pattern patternMen = Pattern.compile("\\b[Mm]en\\b");
		Pattern patternWomen = Pattern.compile("\\b[Ww]omen\\b");
		if (patternMen.matcher(label).find()) {
			return Distinction.MENS;
		}
		if (patternWomen.matcher(label).find()) {
			return Distinction.WOMENS;
		}
		return Distinction.MIXED;
	}
	
	public Event save(Event event) throws UndefinedEntityManagerException {
		if (entityManager == null) {
			String message = String.format("The entity manager is undefined for the class %s", this.getClass());
			throw new UndefinedEntityManagerException(message);
		}
		if (events.add(event)) {			
			entityManager.persist(event);
		} else {
			return find(event);
		}
		return event;
	}
	
	public Event find(Event event) {
		return events.stream()
			.filter(o -> Objects.equals(o, event))
			.findFirst()
			.orElse(event);		
	}
	
	public Set<Event> getEvents() {
		return Collections.unmodifiableSet(events);
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
