package tp_jeux_olympiques;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import tp_jeux_olympiques.enums.LanguageISOCode;

public class TestLanguageISOCode {

	@Test
	void test_converter() {
		LanguageISOCode.Converter conv = new LanguageISOCode.Converter();
		LanguageISOCode attr = conv.convertToEntityAttribute("fr");
		assertEquals(attr, LanguageISOCode.FRENCH);
		assertEquals("en", conv.convertToDatabaseColumn(LanguageISOCode.ENGLISH));
		
		assertNull(conv.convertToDatabaseColumn(null));
		assertNull(conv.convertToEntityAttribute(null));
	}
	
}
