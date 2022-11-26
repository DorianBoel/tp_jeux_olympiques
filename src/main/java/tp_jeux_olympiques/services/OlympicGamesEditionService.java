package tp_jeux_olympiques.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.EntityManager;
import tp_jeux_olympiques.entities.City;
import tp_jeux_olympiques.entities.OlympicGamesEdition;
import tp_jeux_olympiques.enums.LineIndex;
import tp_jeux_olympiques.enums.Season;
import tp_jeux_olympiques.interfaces.Service;

public class OlympicGamesEditionService implements Service<OlympicGamesEdition> {

	private EntityManager entityManager;
	
	private Set<OlympicGamesEdition> olympicGamesEditions = new HashSet<>();
	
	public OlympicGamesEditionService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public OlympicGamesEdition parse(List<String> lineValues, City city) {
		String yearStr = lineValues.get(LineIndex.GAMES_YEAR.INDEX);
		String seasonStr = lineValues.get(LineIndex.GAMES_SEASON.INDEX);
		int year = Integer.parseInt(yearStr);
		Season season = Season.valueOf(seasonStr.toUpperCase());
		return create(year, season, city);
	}

	public OlympicGamesEdition create(int year, Season season, City city) {
		return new OlympicGamesEdition(year, season, city);
	}
	
	public OlympicGamesEdition register(OlympicGamesEdition games) {
		if (olympicGamesEditions.add(games)) {			
			save(games);
			return games;
		}
		return find(games);
	}
	
	private void save(OlympicGamesEdition games) {
		entityManager.persist(games);
	}
	
	public OlympicGamesEdition find(OlympicGamesEdition games) {
		return olympicGamesEditions.stream()
			.filter(o -> Objects.equals(o, games))
			.findFirst()
			.orElse(games);		
	}
	
	public Set<OlympicGamesEdition> getRegistered() {
		return Collections.unmodifiableSet(olympicGamesEditions);
	}
	
}
