package tp_jeux_olympiques.services;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.EntityManager;
import tp_jeux_olympiques.entities.Country;
import tp_jeux_olympiques.entities.Team;
import tp_jeux_olympiques.enums.LineIndex;
import tp_jeux_olympiques.interfaces.Service;

public class TeamService implements Service<Team> {

	private EntityManager entityManager;
	private CountryService countryService;
	
	private Set<Team> teams = new HashSet<>();
	
	public TeamService(EntityManager entityManager, CountryService countryService) {
		this.entityManager = entityManager;
		this.countryService = countryService;
	}
	
	private void save(Team team) {
		entityManager.persist(team);
	}
	
	private Country parseCountry(String code, List<String> countryLines) {
		for (String line : countryLines) {
			List<String> countryLineValues = Arrays.asList(line.split(LineIndex.SEPARATOR_SEMICOLON));
			String countryIOC = countryLineValues.get(LineIndex.COUNTRY_CIO.INDEX);
			String countryName = countryLineValues.get(LineIndex.COUNTRY_NAME_EN.INDEX);
			if (countryIOC.equals(code)) {
				return countryService.findByName(countryName);
			}
		}
		return null;
	}
	
	private Team find(Team team) {
		return teams.stream()
			.filter(o -> Objects.equals(o, team))
			.findAny()
			.orElse(team);		
	}

	public Team create(String name, String codeIoc, Country country) {
		return new Team(name, codeIoc, country);
	}
	
	public Team parse(List<String> lineValues, List<String> countryLines) {
		String name = lineValues.get(LineIndex.TEAM_NAME.INDEX);
		String code = lineValues.get(LineIndex.TEAM_CODE.INDEX);
		Country country = parseCountry(code, countryLines);
		return create(name, code, country);
	}
	
	@Override
	public Team register(Team team) {
		if (teams.add(team)) {			
			save(team);
			return team;
		}
		return find(team);
	}
	
	@Override
	public Set<Team> getEntitySet() {
		return Collections.unmodifiableSet(teams);
	}
	
}
