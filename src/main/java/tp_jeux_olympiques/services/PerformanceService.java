package tp_jeux_olympiques.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.EntityManager;
import tp_jeux_olympiques.LineIndex;
import tp_jeux_olympiques.UndefinedEntityManagerException;
import tp_jeux_olympiques.entities.Athlete;
import tp_jeux_olympiques.entities.Event;
import tp_jeux_olympiques.entities.OlympicGamesEdition;
import tp_jeux_olympiques.entities.Performance;
import tp_jeux_olympiques.entities.Team;
import tp_jeux_olympiques.enums.Medal;

public class PerformanceService {

	private EntityManager entityManager;
	
	private Set<Performance> performances = new HashSet<>();
	
	public Performance parse(List<String> lineValues, Athlete athlete, Event event, Team team, OlympicGamesEdition games) {
		String medalStr = lineValues.get(LineIndex.MEDAL.INDEX);
		Medal medal = parseMedal(medalStr);
		return create(athlete, event, team, games, medal);
	}

	public Performance create(Athlete athlete, Event event, Team team, OlympicGamesEdition games, Medal medal) {
		return new Performance(athlete, event, team, games, medal);
	}
	
	public Medal parseMedal(String str) {
		Medal parsed = null;
		for (Medal medal : Medal.values()) {
			if (medal.toString().equalsIgnoreCase(str)) {
				parsed = medal;
			}
		}
		return parsed;
	}
	
	public void save(Performance performance) throws UndefinedEntityManagerException {
		if (entityManager == null) {
			String message = String.format("The entity manager is undefined for the class %s", this.getClass());
			throw new UndefinedEntityManagerException(message);
		}
		if (performances.add(performance)) {			
			entityManager.persist(performance);
		}
	}
	
	public Set<Performance> getAthlete() {
		return Collections.unmodifiableSet(performances);
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
