package tp_jeux_olympiques.general;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

public class GeneralUtils {

	public static final String SEPARATOR_SEMICOLON = ";";
	
	public static List<String> getLineValues (String line) {
		return Arrays.asList(line.split(SEPARATOR_SEMICOLON));
	}
	
	public static String unescapeDoubleQuotes(String str) {
		return RegExUtils.replaceAll(str, "\"(?!\")", StringUtils.EMPTY);
	}

}
