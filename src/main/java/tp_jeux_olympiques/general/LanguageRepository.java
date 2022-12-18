package tp_jeux_olympiques.general;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jakarta.persistence.EntityManager;
import tp_jeux_olympiques.entities.Language;
import tp_jeux_olympiques.enums.LanguageISOCode;

public class LanguageRepository {
	
	private static Set<Language> languages = Set.of(
		new Language("English", LanguageISOCode.ENGLISH),
		new Language("French", LanguageISOCode.FRENCH)
	);
	
	private EntityManager entityManager;
	
	private Map<LanguageISOCode, Language> languageMap = new HashMap<>();
	
	public LanguageRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
		for (Language language : languages) {
			languageMap.put(language.getISOCode(), language);
			save(language);
		}
	}
	
	private void save(Language language) {
		entityManager.persist(language);
	}
	
	public Language getLanguage(LanguageISOCode iso) {
		return languageMap.get(iso);
	}
	
}