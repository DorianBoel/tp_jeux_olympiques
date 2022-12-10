package tp_jeux_olympiques;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.EntityManager;
import tp_jeux_olympiques.entities.Athlete;
import tp_jeux_olympiques.entities.City;
import tp_jeux_olympiques.entities.Country;
import tp_jeux_olympiques.entities.Event;
import tp_jeux_olympiques.entities.Language;
import tp_jeux_olympiques.entities.OlympicGamesEdition;
import tp_jeux_olympiques.entities.Performance;
import tp_jeux_olympiques.entities.Sport;
import tp_jeux_olympiques.entities.Team;
import tp_jeux_olympiques.entities.Translation;
import tp_jeux_olympiques.enums.CSVFile;
import tp_jeux_olympiques.enums.LineIndex;
import tp_jeux_olympiques.services.AthleteService;
import tp_jeux_olympiques.services.CityService;
import tp_jeux_olympiques.services.CountryService;
import tp_jeux_olympiques.services.EventService;
import tp_jeux_olympiques.services.OlympicGamesEditionService;
import tp_jeux_olympiques.services.PerformanceService;
import tp_jeux_olympiques.services.SportService;
import tp_jeux_olympiques.services.TeamService;
import tp_jeux_olympiques.services.TranslationService;

public class DataLoader {
	
	private FileAccess fileAccess = FileAccess.getInstance();
	private EntityManager entityManager;
	
	private AthleteService athleteService;
	private CityService cityService;
	private LanguageRepository languageRepo;
	private OlympicGamesEditionService olympicGamesEditionService;
	private PerformanceService performanceService;
	private TranslationService translationService;
	private CountryService countryService;
	private SportService sportService;
	private TeamService teamService;
	private EventService eventService;
	
	public DataLoader(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.athleteService = new AthleteService(entityManager);
		this.cityService = new CityService(entityManager);
		this.languageRepo = new LanguageRepository(entityManager);
		this.olympicGamesEditionService = new OlympicGamesEditionService(entityManager);
		this.performanceService = new PerformanceService(entityManager);
		this.translationService = new TranslationService(entityManager, languageRepo);
		this.countryService = new CountryService(entityManager, languageRepo);
		this.sportService = new SportService(entityManager, languageRepo);
		this.teamService = new TeamService(entityManager, countryService);
		this.eventService = new EventService(entityManager, languageRepo, sportService);
	}
	
	public void populate() throws FileNotFoundException {
		Language langFR = languageRepo.getLanguage("fr");
		
		List<String> countryLines = fileAccess.getLines(CSVFile.COUNTRY_CODES);
		List<String> sportLines = fileAccess.getLines(CSVFile.SPORTS_LIST);
		List<String> eventLines = fileAccess.getLines(CSVFile.EVENTS_LIST);
		List<String> performanceLines = fileAccess.getLines(CSVFile.SAMPLE);
		
		for (int i = 1; i < countryLines.size(); i++) {
			List<String> lineValues = splitLine(countryLines.get(i));
			
			Country country = countryService.parse(lineValues);
			countryService.register(country);
			
			Translation translation = translationService.parse(country, langFR, lineValues);
			translationService.register(translation);
		}
		
		for (int i = 1; i < sportLines.size(); i++) {
			List<String> lineValues = splitLine(sportLines.get(i));
			
			Sport sport = sportService.parse(lineValues);
			sportService.register(sport);
			
			Translation translation = translationService.parse(sport, langFR, lineValues);
			translationService.register(translation);
		}
		
		for (int i = 1; i < performanceLines.size(); i++) {
			List<String> lineValues = splitLine(performanceLines.get(i));
			
			Team team = teamService.parse(lineValues, countryLines);
			team = teamService.register(team);
			
			City city = cityService.parse(lineValues);
			city = cityService.register(city);
			
			OlympicGamesEdition games = olympicGamesEditionService.parse(lineValues, city);
			games = olympicGamesEditionService.register(games);
			
			Event event = eventService.parse(lineValues, eventLines);
			event = eventService.register(event);
			
			Translation translation = translationService.parseCompare(event, langFR, eventLines);
			translationService.register(translation);
			
			Athlete athlete = athleteService.parse(lineValues);
			athlete = athleteService.register(athlete);
			
			Performance performance = performanceService.parse(lineValues, athlete, event, team, games);
			performanceService.register(performance);
		}
		entityManager.getTransaction().commit();
	}
	
	private static List<String> splitLine(String line) {
		return Arrays.asList(line.split(LineIndex.SEPARATOR_SEMICOLON));
	}

}
