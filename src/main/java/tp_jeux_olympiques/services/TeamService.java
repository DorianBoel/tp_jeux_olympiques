package tp_jeux_olympiques.services;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.EntityManager;
import tp_jeux_olympiques.LineIndex;
import tp_jeux_olympiques.UndefinedEntityManagerException;
import tp_jeux_olympiques.entities.Country;
import tp_jeux_olympiques.entities.Team;

public class TeamService {

	private EntityManager entityManager;
	private CountryService countryService;
	
	private Set<Team> teams = new HashSet<>();
	
	public TeamService(CountryService countryService) {
		this.countryService = countryService;
	}
	
	public Team parse(List<String> lineValues, List<String> countryLines) {
		String name = lineValues.get(LineIndex.TEAM_NAME.INDEX);
		String code = lineValues.get(LineIndex.TEAM_CODE.INDEX);
		Country country = null;
		for (String line : countryLines) {
			List<String> countryLineValues = Arrays.asList(line.split(LineIndex.SEPARATOR_SEMICOLON));
			String countryIOC = countryLineValues.get(LineIndex.COUNTRY_CIO.INDEX);
			String countryName = countryLineValues.get(LineIndex.COUNTRY_NAME_EN.INDEX);
			if (countryIOC.equals(code)) {
				country = countryService.findByName(countryName);
			}
		}
		return create(name, code, country);
	}

	public Team create(String name, String codeIoc, Country country) {
		return new Team(name, codeIoc, country);
	}
	
	public Team save(Team team) throws UndefinedEntityManagerException {
		if (entityManager == null) {
			String message = String.format("The entity manager is undefined for the class %s", this.getClass());
			throw new UndefinedEntityManagerException(message);
		}
		if (teams.add(team)) {			
			entityManager.persist(team);
		} else {
			return find(team);
		}
		return team;
	}
	
	public Team find(Team team) {
		return teams.stream()
			.filter(o -> Objects.equals(o, team))
			.findFirst()
			.orElse(team);		
	}
	
	public Set<Team> getTeams() {
		return Collections.unmodifiableSet(teams);
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
