package tp_jeux_olympiques.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import jakarta.persistence.EntityManager;
import tp_jeux_olympiques.entities.Athlete;
import tp_jeux_olympiques.enums.LineIndex;
import tp_jeux_olympiques.enums.Sex;
import tp_jeux_olympiques.interfaces.Service;

public class AthleteService implements Service<Athlete> {

	private EntityManager entityManager;
	
	private Set<Athlete> athletes = new HashSet<>();
	
	public AthleteService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public Athlete parse(List<String> lineValues) {
		Function<String, Float> parseFloat = s -> NumberUtils.isCreatable(s) ? NumberUtils.createFloat(s) : null;
		String name = lineValues.get(LineIndex.ATHLETE_NAME.INDEX).replaceAll("\"(?!\")", StringUtils.EMPTY);
		String ageStr = lineValues.get(LineIndex.ATHLETE_AGE.INDEX);
		String heightStr = lineValues.get(LineIndex.ATHLETE_HEIGHT.INDEX);
		String weightStr = lineValues.get(LineIndex.ATHLETE_WEIGHT.INDEX);
		String yearStr = lineValues.get(LineIndex.GAMES_YEAR.INDEX);
		Float height = parseFloat.apply(heightStr);
		Float weight = parseFloat.apply(weightStr);
		Integer birthYear = parseBirthYear(yearStr, ageStr);
		Sex sex = parseSex(lineValues);
		return create(name, birthYear, height, weight, sex);
	}

	public Athlete create(String name, Integer birthYear, Float height, Float weight, Sex sex) {
		return new Athlete(name, birthYear, height, weight, sex);
	}
	
	private Sex parseSex(List<String> lineValues) {
		String sexStr = lineValues.get(LineIndex.ATHLETE_SEX.INDEX);
		for (Sex sex : Sex.values()) {
			if (sex.getLabel().equals(sexStr)) {
				return sex;
			}
		}
		return null;
	}
	
	private Integer parseBirthYear(String yearStr, String ageStr) {
		try {
			int year = Integer.parseInt(yearStr);
			return year - NumberUtils.createInteger(ageStr);
		} catch(NumberFormatException err) {
			return null;
		}
	}
	
	public Athlete register(Athlete athlete) {
		if (athletes.add(athlete)) {			
			save(athlete);
			return athlete;
		}
		return find(athlete);
	}
	
	private void save(Athlete athlete) {
		entityManager.persist(athlete);
	}
	
	public Athlete find(Athlete athlete) {
		return athletes.stream()
			.filter(o -> Objects.equals(o, athlete))
			.findFirst()
			.orElse(athlete);		
	}
	
	public Set<Athlete> getRegistered() {
		return Collections.unmodifiableSet(athletes);
	}
	
}
