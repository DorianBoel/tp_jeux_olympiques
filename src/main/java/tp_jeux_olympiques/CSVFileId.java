package tp_jeux_olympiques;

import java.util.ArrayList;
import java.util.List;

public enum CSVFileId {
	
	SAMPLE("sample",LineIndex.ATHLETE_NAME,
			LineIndex.ATHLETE_SEX,
			LineIndex.ATHLETE_AGE,
			LineIndex.ATHLETE_HEIGHT,
			LineIndex.ATHLETE_WEIGHT,
			LineIndex.TEAM_NAME,
			LineIndex.TEAM_CODE,
			LineIndex.GAMES_YEAR,
			LineIndex.GAMES_YEAR,
			LineIndex.CITY,
			LineIndex.SPORT,
			LineIndex.EVENT,
			LineIndex.MEDAL),
	SPORTS_LIST("liste_des_sports",
		LineIndex.SPORT_LABEL_EN,
		LineIndex.SPORT_LABEL_FR
	),
	EVENTS_LIST(
		"liste_des_epreuves",
		LineIndex.EVENT_LABEL_EN,
		LineIndex.EVENT_LABEL_FR
	),
	COUNTRY_CODES(
		"wikipedia-iso-country-codes",
		LineIndex.COUNTRY_CIO,
		LineIndex.COUNTRY_NAME_FR,
		LineIndex.COUNTRY_NAME_EN,
		LineIndex.COUNTRY_ISO,
		LineIndex.COUNTRY_OBSOLETE
	),
	PERFORMANCES(
		"athlete_epreuves",
		LineIndex.ATHLETE_NAME,
		LineIndex.ATHLETE_SEX,
		LineIndex.ATHLETE_AGE,
		LineIndex.ATHLETE_HEIGHT,
		LineIndex.ATHLETE_WEIGHT,
		LineIndex.TEAM_NAME,
		LineIndex.TEAM_CODE,
		LineIndex.GAMES_YEAR,
		LineIndex.GAMES_YEAR,
		LineIndex.CITY,
		LineIndex.SPORT,
		LineIndex.EVENT,
		LineIndex.MEDAL
	);
	
	private static final String CSV_EXTENSION = ".csv";
	
	private String fileName;
	private List<LineIndex> indexes = new ArrayList<>();
	
	private CSVFileId(String fileName, LineIndex... indexes) {
		this.fileName = fileName + CSV_EXTENSION;
		this.indexes.addAll(List.of(indexes));
	}

	/**
	 * Getter for {@link #fileName}.
	 *
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}
	
	public int indexOfValue(LineIndex lineIndex) {
		return indexes.contains(lineIndex) ? lineIndex.INDEX : -1;
	}
		
}