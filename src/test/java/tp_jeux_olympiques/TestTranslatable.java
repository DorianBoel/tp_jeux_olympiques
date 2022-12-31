package tp_jeux_olympiques;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static tp_jeux_olympiques.UnitTestResources.*;

import org.junit.jupiter.api.Test;

import tp_jeux_olympiques.entities.Sport;
import tp_jeux_olympiques.entities.Translation;

public class TestTranslatable {

	@Test
	void test_translateTo() {
		Sport sport = new Sport("Cross Country Skiing", tr_lang_en);
		sport.getTextContent().addTranslation(new Translation("Ski de fond", tr_lang_fr, null));
		
		assertEquals("Ski de fond", sport.translateTo(tr_lang_fr));
		assertEquals("Cross Country Skiing", sport.translateTo(tr_lang_en));
		assertNull(sport.translateTo(null));
	}
	
}
