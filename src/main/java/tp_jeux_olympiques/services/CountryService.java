package tp_jeux_olympiques.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.EntityManager;
import tp_jeux_olympiques.LanguageRepository;
import tp_jeux_olympiques.entities.Country;
import tp_jeux_olympiques.entities.TextContent;
import tp_jeux_olympiques.enums.LineIndex;
import tp_jeux_olympiques.interfaces.TranslatableService;

public class CountryService implements TranslatableService<Country> {
	
	private EntityManager entityManager;
	private LanguageRepository languageRepo;
	
	private Set<Country> countries = new HashSet<>();
	
	public CountryService(EntityManager entityManager, LanguageRepository languageRepo) {
		this.entityManager = entityManager;
		this.languageRepo = languageRepo;
	}
	
	public Country parse(List<String> lineValues) {
		String name = lineValues.get(LineIndex.COUNTRY_NAME_EN.INDEX);
		String isoCode = parseIsoCode(lineValues);
		boolean obsolete = lineValues.get(LineIndex.COUNTRY_OBSOLETE.INDEX).equals("O");
		TextContent textContent = createTextContent(name, languageRepo.getLanguage("en"));
		return create(textContent, isoCode, obsolete);
	}

	public Country create(TextContent textContent, String codeIso, boolean obsolete) {
		return new Country(textContent, codeIso, obsolete);
	}
	
	public Country register(Country country) {
		if (countries.add(country)) {			
			save(country);
			return country;
		}
		return find(country);
	}
	
	private String parseIsoCode(List<String> lineValues) {
		String isoCode = lineValues.get(LineIndex.COUNTRY_ISO.INDEX);
		return isoCode.length() > 0 ? isoCode : null;
	}
	
	private void save(Country country) {
		entityManager.persist(country);
	}
	
	public Country find(Country country) {
		return countries.stream()
				.filter(o -> Objects.equals(o, country))
				.findFirst()
				.orElse(null);
	}
	
	public Country findByName(String name) {
		return countries.stream()
			.filter(c -> c.getName().equals(name))
			.findFirst()
			.orElse(null);
	}
	
	public Set<Country> getRegistered() {
		return Collections.unmodifiableSet(countries);
	}
	
}