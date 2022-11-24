package tp_jeux_olympiques.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.EntityManager;
import tp_jeux_olympiques.LineIndex;
import tp_jeux_olympiques.UndefinedEntityManagerException;
import tp_jeux_olympiques.entities.City;
import tp_jeux_olympiques.entities.OlympicGamesEdition;
import tp_jeux_olympiques.enums.Season;

public class OlympicGamesEditionService {

	private EntityManager entityManager;
	
	private Set<OlympicGamesEdition> olympicGamesEditions = new HashSet<>();
	
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
	
	public OlympicGamesEdition save(OlympicGamesEdition games) throws UndefinedEntityManagerException {
		if (entityManager == null) {
			String message = String.format("The entity manager is undefined for the class %s", this.getClass());
			throw new UndefinedEntityManagerException(message);
		}
		if (olympicGamesEditions.add(games)) {			
			entityManager.persist(games);
		} else {
			return find(games);
		}
		return games;
	}
	
	public OlympicGamesEdition find(OlympicGamesEdition games) {
		return olympicGamesEditions.stream()
			.filter(o -> Objects.equals(o, games))
			.findFirst()
			.orElse(games);		
	}
	
	public Set<OlympicGamesEdition> getOlympicGamesEdition() {
		return Collections.unmodifiableSet(olympicGamesEditions);
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
