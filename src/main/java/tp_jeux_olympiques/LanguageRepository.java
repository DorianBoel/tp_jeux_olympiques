package tp_jeux_olympiques;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jakarta.persistence.EntityManager;
import tp_jeux_olympiques.entities.Language;

public class LanguageRepository {
	
	private static Set<Language> languages = Set.of(
		new Language("English", "EN"),
		new Language("French", "FR")
	);
	
	private EntityManager entityManager;
	
	private Map<String, Language> languageMap = new HashMap<>();
	
	public LanguageRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
		for (Language language : languages) {
			languageMap.put(language.getCodeISO().toLowerCase(), language);
			save(language);
		}
	}
	
	private void save(Language language) {
		entityManager.persist(language);
	}
	
	public Language getLanguage(String iso) {
		return languageMap.get(iso.toLowerCase());
	}
	
}