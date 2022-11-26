package tp_jeux_olympiques.services;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.EntityManager;
import tp_jeux_olympiques.LanguageRepository;
import tp_jeux_olympiques.entities.Country;
import tp_jeux_olympiques.entities.Event;
import tp_jeux_olympiques.entities.Language;
import tp_jeux_olympiques.entities.Sport;
import tp_jeux_olympiques.entities.Translation;
import tp_jeux_olympiques.enums.LineIndex;
import tp_jeux_olympiques.interfaces.Service;
import tp_jeux_olympiques.interfaces.Translatable;

public class TranslationService implements Service<Translation> {
	
	private EntityManager entityManager;
	private LanguageRepository languageRepo;
	
	private Set<Translation> translations = new HashSet<>();
	
	private Map<Class<? extends Translatable>, Map<Language, LineIndex>> translationIndexMap = new HashMap<>();
	
	public TranslationService(EntityManager entityManager, LanguageRepository languageRepo) {
		this.entityManager = entityManager;
		this.languageRepo = languageRepo;
		loadTranslationMap();
	}
	
	private void loadTranslationMap() {
		Language langEN = languageRepo.getLanguage("en");
		Language langFR = languageRepo.getLanguage("fr");
		
		Map<Language, LineIndex> tlCountry = new HashMap<>();
		tlCountry.put(langEN, LineIndex.COUNTRY_NAME_EN);
		tlCountry.put(langFR, LineIndex.COUNTRY_NAME_FR);
		translationIndexMap.put(Country.class, tlCountry);
		
		Map<Language, LineIndex> tlSport = new HashMap<>();
		tlSport.put(langEN, LineIndex.SPORT_LABEL_EN);
		tlSport.put(langFR, LineIndex.SPORT_LABEL_FR);
		translationIndexMap.put(Sport.class, tlSport);
		
		Map<Language, LineIndex> tlEvent = new HashMap<>();
		tlEvent.put(langEN, LineIndex.EVENT_LABEL_EN);
		tlEvent.put(langFR, LineIndex.EVENT_LABEL_FR);
		translationIndexMap.put(Event.class, tlEvent);
	}
	
	public Translation parse(Translatable object, Language language, List<String> lineValues) {
		LineIndex lineIdx = translationIndexMap.get(object.getClass()).get(language);
		String translationValue = lineValues.get(lineIdx.INDEX);
		return create(object, language, translationValue);
	}
	
	public Translation parseCompare(Translatable object, Language language, List<String> dataLines) {
		String parsed = null;
		String textValue = object.getTextContent().getText();
		LineIndex lineIdxEN = translationIndexMap.get(object.getClass()).get(languageRepo.getLanguage("en"));
		LineIndex lineIdxTranslate = translationIndexMap.get(object.getClass()).get(languageRepo.getLanguage("fr"));
		for (String line : dataLines) {
			List<String> dataLineValues = Arrays.asList(line.split(LineIndex.SEPARATOR_SEMICOLON));
			String labelEN = dataLineValues.get(lineIdxEN.INDEX);
			if (labelEN.equals(textValue)) {
				parsed = dataLineValues.get(lineIdxTranslate.INDEX);
			}
		}
		return create(object, language, parsed);
	}
	
	public Translation create(Translatable object, Language language, String text) {
		return new Translation(text, language, object.getTextContent());
	}
	
	@Override
	public Translation register(Translation translation) {
		if (translations.add(translation)) {
			save(translation);
			return translation;
		}
		return find(translation);
	}
	
	private void save(Translation translation) {	
		entityManager.persist(translation);
	}
	
	public Translation find(Translation translation) {
		return translations.stream()
			.filter(o -> Objects.equals(o, translation))
			.findFirst()
			.orElse(translation);		
	}
	
	@Override
	public Set<Translation> getRegistered() {
		return Collections.unmodifiableSet(translations);
	}
	
	public Map<Class<? extends Translatable>, Map<Language, LineIndex>> getTranslationindexMap() {
		return Collections.unmodifiableMap(translationIndexMap);
	}
	
}
