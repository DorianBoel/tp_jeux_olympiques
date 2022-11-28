package tp_jeux_olympiques.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.EntityManager;
import tp_jeux_olympiques.entities.Athlete;
import tp_jeux_olympiques.entities.Event;
import tp_jeux_olympiques.entities.OlympicGamesEdition;
import tp_jeux_olympiques.entities.Performance;
import tp_jeux_olympiques.entities.Team;
import tp_jeux_olympiques.enums.LineIndex;
import tp_jeux_olympiques.enums.Medal;
import tp_jeux_olympiques.interfaces.Service;

public class PerformanceService implements Service<Performance> {

	private EntityManager entityManager;
	
	private Set<Performance> performances = new HashSet<>();
	
	public PerformanceService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public Performance parse(List<String> lineValues, Athlete athlete, Event event, Team team, OlympicGamesEdition games) {
		Medal medal = parseMedal(lineValues);
		return create(athlete, event, team, games, medal);
	}

	public Performance create(Athlete athlete, Event event, Team team, OlympicGamesEdition games, Medal medal) {
		return new Performance(athlete, event, team, games, medal);
	}
	
	public Medal parseMedal(List<String> lineValues) {
		String medalStr = lineValues.get(LineIndex.MEDAL.INDEX);
		for (Medal medal : Medal.values()) {
			if (medal.toString().equalsIgnoreCase(medalStr)) {
				return medal;
			}
		}
		return null;
	}
	
	public Performance register(Performance performance) {
		if (performances.add(performance)) {			
			save(performance);
			return performance;
		}
		return find(performance);
	}
	
	private void save(Performance performance) {
		entityManager.persist(performance);
	}
	
	public Performance find(Performance performance) {
		return performances.stream()
			.filter(o -> Objects.equals(o, performance))
			.findFirst()
			.orElse(performance);
	}
	
	public Set<Performance> getRegistered() {
		return Collections.unmodifiableSet(performances);
	}
	
}
