package tp_jeux_olympiques;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import tp_jeux_olympiques.entities.Language;
import tp_jeux_olympiques.enums.LanguageISOCode;
import tp_jeux_olympiques.services.AthleteService;

public class UnitTestResources {
	
	public static EntityManagerFactory tr_emf = Persistence.createEntityManagerFactory("unit_test");
	public static EntityManager tr_em = tr_emf.createEntityManager();
	
	public static AthleteService tr_athlete_srv = new AthleteService(tr_em);
	
	public static Language tr_lang_en = new Language("English", LanguageISOCode.ENGLISH);
	public static Language tr_lang_fr = new Language("French", LanguageISOCode.FRENCH);
	
}
