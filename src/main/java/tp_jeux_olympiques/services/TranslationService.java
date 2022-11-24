package tp_jeux_olympiques.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.EntityManager;
import tp_jeux_olympiques.UndefinedEntityManagerException;
import tp_jeux_olympiques.entities.Language;
import tp_jeux_olympiques.entities.Translation;
import tp_jeux_olympiques.interfaces.Translatable;

public class TranslationService {
	
	private EntityManager entityManager;
	
	public Set<Translation> translations = new HashSet<>();
	
	public Translation parse(Translatable object, Language language, List<String> lineValues) {
		String translationValue = lineValues.get(object.getTranslationIndex(language).INDEX);
		return create(object, language, translationValue);
	}
	
	public Translation create(Translatable object, Language language, String text) {
		return new Translation(text, language, object.getTextContent());
	}
	
	public void save(Translation translation) throws UndefinedEntityManagerException {
		if (entityManager == null) {
			String message = String.format("The entity manager is undefined for the class %", this.getClass());
			throw new UndefinedEntityManagerException(message);
		}
		if (translations.add(translation)) {			
			entityManager.persist(translation);
		}
		translations.add(translation);
	}
	
	public Set<Translation> getTranslation() {
		return Collections.unmodifiableSet(translations);
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
