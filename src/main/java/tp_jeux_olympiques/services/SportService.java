package tp_jeux_olympiques.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.EntityManager;
import tp_jeux_olympiques.LanguageRepository;
import tp_jeux_olympiques.LineIndex;
import tp_jeux_olympiques.UndefinedEntityManagerException;
import tp_jeux_olympiques.entities.Language;
import tp_jeux_olympiques.entities.Sport;

public class SportService {

	private EntityManager entityManager;
	private LanguageRepository languageRepository = LanguageRepository.getInstance();
	
	private Set<Sport> sports = new HashSet<>();
	
	public Sport parse(List<String> lineValues) {
		String nameEN = lineValues.get(LineIndex.SPORT_LABEL_EN.INDEX);
		return create(nameEN, languageRepository.get("en"));
	}

	public Sport create(String label, Language language) {
		return new Sport(label, language);
	}
	
	public void save(Sport sport) throws UndefinedEntityManagerException {
		if (entityManager == null) {
			String message = String.format("The entity manager is undefined for the class %s", this.getClass());
			throw new UndefinedEntityManagerException(message);
		}
		if (sports.add(sport)) {			
			entityManager.persist(sport);
		}
		sports.add(sport);
	}
	
	public Sport findByLabel(String label) {
		return sports.stream()
			.filter(c -> c.getLabel().equals(label))
			.findFirst()
			.orElse(null);
	}
	
	public Set<Sport> getSports() {
		return Collections.unmodifiableSet(sports);
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
