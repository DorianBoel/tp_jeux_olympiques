package tp_jeux_olympiques.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.EntityManager;
import tp_jeux_olympiques.LanguageRepository;
import tp_jeux_olympiques.entities.Sport;
import tp_jeux_olympiques.entities.TextContent;
import tp_jeux_olympiques.enums.LineIndex;
import tp_jeux_olympiques.interfaces.TranslatableService;

public class SportService implements TranslatableService<Sport> {

	private EntityManager entityManager;
	private LanguageRepository languageRepo;
	
	public SportService(EntityManager entityManager, LanguageRepository languageRepo) {
		this.entityManager = entityManager;
		this.languageRepo = languageRepo;
	}
	
	private Set<Sport> sports = new HashSet<>();
	
	public Sport parse(List<String> lineValues) {
		String name = lineValues.get(LineIndex.SPORT_LABEL_EN.INDEX);
		TextContent textContent = createTextContent(name, languageRepo.getLanguage("en"));
		return create(textContent);
	}

	public Sport create(TextContent textContent) {
		return new Sport(textContent);
	}
	
	@Override
	public Sport register(Sport sport) {
		if (sports.add(sport)) {			
			save(sport);
			return sport;
		}
		return find(sport);
	}
	
	private void save(Sport sport) {
		entityManager.persist(sport);
	}
	
	public Sport find(Sport sport) {
		return sports.stream()
			.filter(o -> Objects.equals(o, sport))
			.findFirst()
			.orElse(sport);	
	}
	
	public Sport findByLabel(String label) {
		return sports.stream()
			.filter(c -> c.getLabel().equals(label))
			.findFirst()
			.orElse(null);
	}
	
	public Set<Sport> getRegistered() {
		return Collections.unmodifiableSet(sports);
	}
	
}
