package tp_jeux_olympiques.services;

import java.util.AbstractMap;
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
		
		Map<Language, LineIndex> tlCountry = Map.ofEntries(
			new AbstractMap.SimpleEntry<Language, LineIndex>(langEN, LineIndex.COUNTRY_NAME_EN),
			new AbstractMap.SimpleEntry<Language, LineIndex>(langFR, LineIndex.COUNTRY_NAME_FR)
		);
		translationIndexMap.put(Country.class, tlCountry);
		
		Map<Language, LineIndex> tlSport = Map.ofEntries(
			new AbstractMap.SimpleEntry<Language, LineIndex>(langEN, LineIndex.SPORT_LABEL_EN),
			new AbstractMap.SimpleEntry<Language, LineIndex>(langFR, LineIndex.SPORT_LABEL_FR)
		);
		translationIndexMap.put(Sport.class, tlSport);
		
		Map<Language, LineIndex> tlEvent = Map.ofEntries(
			new AbstractMap.SimpleEntry<Language, LineIndex>(langEN, LineIndex.EVENT_LABEL_EN),
			new AbstractMap.SimpleEntry<Language, LineIndex>(langFR, LineIndex.EVENT_LABEL_FR)
		);
		translationIndexMap.put(Event.class, tlEvent);
	}
	
	private void save(Translation translation) {	
		entityManager.persist(translation);
	}
	
	private Translation find(Translation translation) {
		return translations.stream()
			.filter(o -> Objects.equals(o, translation))
			.findAny()
			.orElse(translation);		
	}
	
	public Translation create(Translatable object, Language language, String text) {
		return new Translation(text, language, object.getTextContent());
	}
	
	public Translation parse(Translatable object, Language language, List<String> lineValues) {
		LineIndex lineIdx = translationIndexMap.get(object.getClass()).get(language);
		String translationValue = lineValues.get(lineIdx.INDEX);
		return create(object, language, translationValue);
	}
	
	public Translation parseCompare(Translatable object, Language language, List<String> dataLines) {
		String parsed = null;
		Class<? extends Translatable> tClass = object.getClass();
		String textValue = object.getTextContent().getText();
		LineIndex lineIdxEN = translationIndexMap.get(tClass).get(languageRepo.getLanguage("en"));
		LineIndex lineIdxTranslate = translationIndexMap.get(tClass).get(language);
		for (String line : dataLines) {
			List<String> dataLineValues = LineIndex.getLineValues(line);
			String labelEN = dataLineValues.get(lineIdxEN.INDEX);
			if (labelEN.equals(textValue)) {
				parsed = dataLineValues.get(lineIdxTranslate.INDEX);
			}
		}
		return create(object, language, parsed);
	}
	
	public Map<Class<? extends Translatable>, Map<Language, LineIndex>> getTranslationIndexMap() {
		return Collections.unmodifiableMap(translationIndexMap);
	}
	
	@Override
	public Translation register(Translation translation) {
		if (translations.add(translation)) {
			save(translation);
			return translation;
		}
		return find(translation);
	}
	
	@Override
	public Set<Translation> getEntitySet() {
		return Collections.unmodifiableSet(translations);
	}
	
}
