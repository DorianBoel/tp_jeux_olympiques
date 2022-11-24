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

public class CityService {

	private EntityManager entityManager;
	
	private Set<City> cities = new HashSet<>();
	
	public City parse(List<String> lineValues) {
		String name = lineValues.get(LineIndex.CITY.INDEX);
		return create(name);
	}

	public City create(String name) {
		return new City(name);
	}
	
	public City save(City city) throws UndefinedEntityManagerException {
		if (entityManager == null) {
			String message = String.format("The entity manager is undefined for the class %s", this.getClass());
			throw new UndefinedEntityManagerException(message);
		}
		if (cities.add(city)) {			
			entityManager.persist(city);
		} else {
			return find(city);
		}
		return city;
	}
	
	public City find(City city) {
		return cities.stream()
			.filter(o -> Objects.equals(o, city))
			.findFirst()
			.orElse(city);		
	}
	
	public Set<City> getCities() {
		return Collections.unmodifiableSet(cities);
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
