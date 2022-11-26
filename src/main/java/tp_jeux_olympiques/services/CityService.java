package tp_jeux_olympiques.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.EntityManager;
import tp_jeux_olympiques.entities.City;
import tp_jeux_olympiques.enums.LineIndex;
import tp_jeux_olympiques.interfaces.Service;

public class CityService implements Service<City> {

	private EntityManager entityManager;
	
	private Set<City> cities = new HashSet<>();
	
	public CityService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public City parse(List<String> lineValues) {
		String name = lineValues.get(LineIndex.CITY.INDEX);
		return create(name);
	}

	public City create(String name) {
		return new City(name);
	}
	
	public City register(City city) {
		if (cities.add(city)) {			
			save(city);
			return city;
		}
		return find(city);
	}
	
	private void save(City city) {
		entityManager.persist(city);
	}
	
	public City find(City city) {
		return cities.stream()
			.filter(o -> Objects.equals(o, city))
			.findFirst()
			.orElse(city);		
	}
	
	public Set<City> getRegistered() {
		return Collections.unmodifiableSet(cities);
	}
	
}
