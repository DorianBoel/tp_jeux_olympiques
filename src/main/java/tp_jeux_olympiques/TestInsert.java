package tp_jeux_olympiques;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
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
import tp_jeux_olympiques.enums.Distinction;
import tp_jeux_olympiques.enums.Medal;
import tp_jeux_olympiques.enums.Season;
import tp_jeux_olympiques.enums.Sex;
import tp_jeux_olympiques.utils.TranslationFactory;

public class TpJeuxOlympiques {

	public static void main(String[] args) {
		
		TranslationFactory translationFactory = new TranslationFactory();

		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("cda_tp_jpa_jeux_olympiques");
		EntityManager entityManager = emFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		Language en = new Language("English", "EN");
		Language fr = new Language("French", "FR");
		
		entityManager.persist(en);
		entityManager.persist(fr);
		
		Sport sport1 = new Sport("Aeronautics", en);
		Translation sport1tl = translationFactory.createTranslation(sport1, fr, "Aéronautique");
		
		Event event1 = new Event("Mixed Aeronautics", en, Distinction.MIXED, sport1);
		Translation event1tl = translationFactory.createTranslation(event1, fr, "Traversée des Alpes en planeur");
		
		Country country1 = new Country("Switzerland", en, "CHE", false);
		Translation country1tl = translationFactory.createTranslation(country1, fr, "Suisse");
		
		Team team1 = new Team("Switzerland", "SUI", country1);
		
		City city1 = new City("Berlin");
		
		OlympicGamesEdition og1 = new OlympicGamesEdition(1936, Season.SUMMER, city1);
		
		Athlete athlete1 = new Athlete("Hermann Schreiber", og1.getYear() - 26, null, null, Sex.MALE);
		
		Performance performance1 = new Performance(athlete1, event1, team1, og1, Medal.GOLD);
		
		entityManager.persist(sport1);
		entityManager.persist(sport1tl);
		entityManager.persist(event1);
		entityManager.persist(event1tl);
		entityManager.persist(country1);
		entityManager.persist(country1tl);
		entityManager.persist(team1);
		entityManager.persist(city1);
		entityManager.persist(og1);
		entityManager.persist(athlete1);
		entityManager.persist(performance1);
		
		entityManager.getTransaction().commit();
		
		Team te = entityManager.find(Team.class, 1);
		
		System.out.println();
		country1.getTeams().forEach(t -> System.out.println(t.getName()));
		System.out.println(te.getCodeIOC());
		System.out.println();
		
		entityManager.close();
		emFactory.close();
		
	}

}
