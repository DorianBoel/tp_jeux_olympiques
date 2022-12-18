package tp_jeux_olympiques.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.EntityManager;
import tp_jeux_olympiques.entities.Country;
import tp_jeux_olympiques.entities.TextContent;
import tp_jeux_olympiques.enums.LanguageISOCode;
import tp_jeux_olympiques.enums.LineIndex;
import tp_jeux_olympiques.general.LanguageRepository;
import tp_jeux_olympiques.interfaces.TranslatableService;

public class CountryService implements TranslatableService<Country> {
	
	private EntityManager entityManager;
	private LanguageRepository languageRepo;
	
	private Set<Country> countries = new HashSet<>();
	
	public CountryService(EntityManager entityManager, LanguageRepository languageRepo) {
		this.entityManager = entityManager;
		this.languageRepo = languageRepo;
	}
	
	private void save(Country country) {
		entityManager.persist(country);
	}
	
	private Country find(Country country) {
		return countries.stream()
			.filter(o -> Objects.equals(o, country))
			.findAny()
			.orElse(null);
	}
	
	private String parseIsoCode(List<String> lineValues) {
		String isoCode = lineValues.get(LineIndex.COUNTRY_ISO.getIndex());
		return isoCode.length() > 0 ? isoCode : null;
	}

	public Country create(TextContent textContent, String codeIso, boolean obsolete) {
		return new Country(textContent, codeIso, obsolete);
	}
	
	public Country parse(List<String> lineValues) {
		String name = lineValues.get(LineIndex.COUNTRY_NAME_EN.getIndex());
		String isoCode = parseIsoCode(lineValues);
		boolean obsolete = lineValues.get(LineIndex.COUNTRY_OBSOLETE.getIndex()).equals("O");
		TextContent textContent = createTextContent(name, languageRepo.getLanguage(LanguageISOCode.ENGLISH));
		return create(textContent, isoCode, obsolete);
	}
	
	public Country findByName(String name) {
		return countries.stream()
			.filter(c -> c.getName().equals(name))
			.findAny()
			.orElse(null);
	}
	
	@Override
	public Country register(Country country) {
		if (countries.add(country)) {			
			save(country);
			return country;
		}
		return find(country);
	}
	
	@Override
	public Set<Country> getEntitySet() {
		return Collections.unmodifiableSet(countries);
	}
	
}