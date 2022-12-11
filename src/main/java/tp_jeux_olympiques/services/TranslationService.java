package tp_jeux_olympiques.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.EntityManager;
import tp_jeux_olympiques.entities.Language;
import tp_jeux_olympiques.entities.Translation;
import tp_jeux_olympiques.enums.LineIndex;
import tp_jeux_olympiques.general.LanguageRepository;
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
		for (Translatable.TranslatableType translatableType : Translatable.TranslatableType.values()) {
			Map<Language, LineIndex> tlMap = new HashMap<>();
			for (Map.Entry<String, LineIndex> entry : translatableType.getTranslationIndexes().entrySet()) {
				tlMap.put(languageRepo.getLanguage(entry.getKey()), entry.getValue());
			}
			translationIndexMap.put(translatableType.getImplementation(), tlMap);
		}
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
