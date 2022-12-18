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
import tp_jeux_olympiques.enums.Gender;
import tp_jeux_olympiques.interfaces.Service;

public class AthleteService implements Service<Athlete> {

	private EntityManager entityManager;
	
	private Set<Athlete> athletes = new HashSet<>();
	
	public AthleteService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	private void save(Athlete athlete) {
		entityManager.persist(athlete);
	}
	
	private Athlete find(Athlete athlete) {
		return athletes.stream()
			.filter(o -> Objects.equals(o, athlete))
			.findAny()
			.orElse(athlete);		
	}
	
	private Gender parseGender(List<String> lineValues) {
		String genderStr = lineValues.get(LineIndex.ATHLETE_GENDER.getIndex());
		for (Gender gender : Gender.values()) {
			if (gender.getLabel().equals(genderStr)) {
				return gender;
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

	public Athlete create(String name, Integer birthYear, Float height, Float weight, Gender gender) {
		return new Athlete(name, birthYear, height, weight, gender);
	}
	
	public Athlete parse(List<String> lineValues) {
		Function<String, Float> parseFloat = s -> NumberUtils.isCreatable(s) ? NumberUtils.createFloat(s) : null;
		String name = lineValues.get(LineIndex.ATHLETE_NAME.getIndex()).replaceAll("\"(?!\")", StringUtils.EMPTY);
		name = name.trim();
		String ageStr = lineValues.get(LineIndex.ATHLETE_AGE.getIndex());
		String heightStr = lineValues.get(LineIndex.ATHLETE_HEIGHT.getIndex());
		String weightStr = lineValues.get(LineIndex.ATHLETE_WEIGHT.getIndex());
		String yearStr = lineValues.get(LineIndex.GAMES_YEAR.getIndex());
		Float height = parseFloat.apply(heightStr);
		Float weight = parseFloat.apply(weightStr);
		Integer birthYear = parseBirthYear(yearStr, ageStr);
		Gender gender = parseGender(lineValues);
		return create(name, birthYear, height, weight, gender);
	}
	
	@Override
	public Athlete register(Athlete athlete) {
		if (athletes.add(athlete)) {			
			save(athlete);
			return athlete;
		}
		return find(athlete);
	}
	
	@Override
	public Set<Athlete> getEntitySet() {
		return Collections.unmodifiableSet(athletes);
	}
	
}
