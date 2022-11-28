package tp_jeux_olympiques.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import jakarta.persistence.EntityManager;
import tp_jeux_olympiques.LanguageRepository;
import tp_jeux_olympiques.entities.Event;
import tp_jeux_olympiques.entities.Language;
import tp_jeux_olympiques.entities.Sport;
import tp_jeux_olympiques.entities.TextContent;
import tp_jeux_olympiques.enums.Distinction;
import tp_jeux_olympiques.enums.LineIndex;
import tp_jeux_olympiques.interfaces.TranslatableService;

public class EventService implements TranslatableService<Event> {

	private EntityManager entityManager;
	private LanguageRepository languageRepo;
	private SportService sportService;

	private Set<Event> events = new HashSet<>();
	
	public EventService(EntityManager entityManager, LanguageRepository languageRepo,
			SportService sportService)
	{
		this.entityManager = entityManager;
		this.languageRepo = languageRepo;
		this.sportService = sportService;
	}
	
	public Event parse(List<String> lineValues, List<String> eventLines) {
		String label = lineValues.get(LineIndex.EVENT.INDEX);
		String sportName = lineValues.get(LineIndex.SPORT.INDEX);
		Distinction distinction = parseDistinction(label);
		String sportNameStart = sportName + StringUtils.SPACE;
		if (label.startsWith(sportNameStart)) {
			label = label.replace(sportNameStart, StringUtils.EMPTY);
		}
		Sport sport = sportService.findByLabel(sportName);
		TextContent textContent = createTextContent(label, languageRepo.getLanguage("en"));
		Event event = create(textContent, distinction, sport);
		return event;
	}

	public Event create(TextContent textContent, Distinction distinction, Sport sport) {
		return new Event(textContent, distinction, sport);
	}
	
	public TextContent createTextContent(String text, Language language) {
		return new TextContent(text, languageRepo.getLanguage("en"));
	}
	
	private Distinction parseDistinction(String label) {
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
	
	public Event register(Event event) {
		if (events.add(event)) {			
			save(event);
			return event;
		}
		return find(event);
	}
	
	private void save(Event event) {
		entityManager.persist(event);
	}
	
	public Event find(Event event) {
		return events.stream()
			.filter(o -> Objects.equals(o, event))
			.findFirst()
			.orElse(event);		
	}
	
	public Set<Event> getRegistered() {
		return Collections.unmodifiableSet(events);
	}
	
}
