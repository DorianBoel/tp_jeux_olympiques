package tp_jeux_olympiques;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import tp_jeux_olympiques.general.GeneralUtils;

public class TestGeneralUtils {

	@Test
	void test_unescapeDoubleQuotes() {
		String str1 = "Cornelia \"\"Cor\"\" Aalten (-Strannood)";
		String str2 = "Cornelia \"Cor\" Aalten (-Strannood)";
		assertEquals(GeneralUtils.unescapeDoubleQuotes(str1), str2);
		assertNull(GeneralUtils.unescapeDoubleQuotes(null));
	}
	
}
