package tp_jeux_olympiques.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
		String name = lineValues.get(LineIndex.ATHLETE_NAME.INDEX).replaceAll("\"(?!\")", "");
		String ageStr = lineValues.get(LineIndex.ATHLETE_AGE.INDEX);
		String heightStr = lineValues.get(LineIndex.ATHLETE_HEIGHT.INDEX);
		String weightStr = lineValues.get(LineIndex.ATHLETE_WEIGHT.INDEX);
		String sexStr = lineValues.get(LineIndex.ATHLETE_SEX.INDEX);
		String yearStr = lineValues.get(LineIndex.GAMES_YEAR.INDEX);
		Integer birthYear;
		Float height, weight;
		Sex sex = parseSex(sexStr);
		try {
			int year = Integer.parseInt(yearStr);
			birthYear = year - Integer.parseInt(ageStr);
		} catch(NumberFormatException err) {
			birthYear = null;
		}
		try {
			height = Float.parseFloat(heightStr);
		} catch(NumberFormatException err) {
			height = null;
		}
		try {
			weight = Float.parseFloat(weightStr);
		} catch(NumberFormatException err) {
			weight = null;
		}
		return create(name, birthYear, height, weight, sex);
	}

	public Athlete create(String name, Integer birthYear, Float height, Float weight, Sex sex) {
		return new Athlete(name, birthYear, height, weight, sex);
	}
	
	public Sex parseSex(String str) {
		Sex parsed = null;
		for (Sex sex : Sex.values()) {
			if (sex.getLabel().equals(str)) {
				parsed = sex;
			}
		}
		return parsed;
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
