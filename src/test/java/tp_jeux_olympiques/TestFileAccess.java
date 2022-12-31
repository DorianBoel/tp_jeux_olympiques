package tp_jeux_olympiques;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import tp_jeux_olympiques.enums.CSVFile;
import tp_jeux_olympiques.general.FileAccess;

public class TestFileAccess {

	@Test
	void test_getLines() throws FileNotFoundException {
		FileAccess fa = FileAccess.getInstance();
		assertEquals(fa.getLines(CSVFile.SPORTS_LIST).size(), 68);
	}
	
}
