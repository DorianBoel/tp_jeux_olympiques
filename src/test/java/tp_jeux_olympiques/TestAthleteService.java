 package tp_jeux_olympiques;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tp_jeux_olympiques.UnitTestResources.tr_athlete_srv;
import static tp_jeux_olympiques.UnitTestResources.tr_em;

import java.util.List;

import org.junit.jupiter.api.Test;

import tp_jeux_olympiques.entities.Athlete;
import tp_jeux_olympiques.enums.Gender;
import tp_jeux_olympiques.services.AthleteService;

class TestAthleteService {

	AthleteService athleteService = tr_athlete_srv;
	
	@Test
	void test_parse() {
		String csvLine = "17;Paavo Johannes Aaltonen;M;32;175;64;Finland;FIN;1952 Summer;1952;Summer;Helsinki;Gymnastics;Gymnastics Men's Individual All-Around;NA";
		Athlete athlete = athleteService.parse(List.of(csvLine.split(";")));
		
		assertEquals(athlete.getGender(), Gender.MALE);
		assertEquals(athlete.getName(), "Paavo Johannes Aaltonen");
		assertEquals(athlete.getHeight(), 175);
		assertEquals(athlete.getWeight(), 64);
		assertEquals(athlete.getBirthYear(), 1920);
	}
	
	@Test
	void test_register() {
		tr_em.getTransaction().begin();
		
		Athlete athlete1 = new Athlete("Paavo Johannes Aaltonen", 1920, 175f, 65f, Gender.MALE);
		Athlete athlete2 = new Athlete("Paavo Johannes Aaltonen", 1920, 175f, 65f, Gender.MALE);
		athleteService.register(athlete1);
		athleteService.register(athlete2);
		
		assertEquals(athleteService.getEntitySet().size(), 1);
	}

}
