package tp_jeux_olympiques.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.EntityManager;
import tp_jeux_olympiques.LanguageRepository;
import tp_jeux_olympiques.LineIndex;
import tp_jeux_olympiques.UndefinedEntityManagerException;
import tp_jeux_olympiques.entities.Country;
import tp_jeux_olympiques.entities.Language;

public class CountryService {
	
	private EntityManager entityManager;
	private LanguageRepository languageRepository = LanguageRepository.getInstance();
	
	private Set<Country> countries = new HashSet<>();
	
	public Country parse(List<String> lineValues) {
		String nameEN = lineValues.get(LineIndex.COUNTRY_NAME_EN.INDEX);
		String isoCode = lineValues.get(LineIndex.COUNTRY_ISO.INDEX);
		isoCode = isoCode.length() > 0 ? isoCode : null;
		String obsoleteStr = lineValues.get(LineIndex.COUNTRY_OBSOLETE.INDEX);
		boolean obsolete = obsoleteStr.equals("O");
		return create(nameEN, languageRepository.get("en"), isoCode, obsolete);
	}

	public Country create(String name, Language language, String codeIso, boolean obsolete) {
		return new Country(name, language, codeIso, obsolete);
	}
	
	public void save(Country country) throws UndefinedEntityManagerException {
		if (entityManager == null) {
			String message = String.format("The entity manager is undefined for the class %", this.getClass());
			throw new UndefinedEntityManagerException(message);
		}
		if (countries.add(country)) {			
			entityManager.persist(country);
		}
		countries.add(country);
	}
	
	public Country findByName(String name) {
		return countries.stream()
			.filter(c -> c.getName().equals(name))
			.findFirst()
			.orElse(null);
	}
	
	public Set<Country> getCountries() {
		return Collections.unmodifiableSet(countries);
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}