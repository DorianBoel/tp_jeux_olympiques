package tp_jeux_olympiques;

public enum LineIndex {

	ATHLETE_NAME(1),
	ATHLETE_SEX(2),
	ATHLETE_AGE(3),
	ATHLETE_HEIGHT(4),
	ATHLETE_WEIGHT(5),
	TEAM_NAME(6),
	TEAM_CODE(7),
	GAMES_YEAR(9),
	GAMES_SEASON(10),
	CITY(11),
	SPORT(12),
	EVENT(13),
	MEDAL(14),
	SPORT_LABEL_EN(0),
	SPORT_LABEL_FR(1),
	EVENT_LABEL_EN(0),
	EVENT_LABEL_FR(1),
	COUNTRY_CIO(0),
	COUNTRY_NAME_FR(1),
	COUNTRY_NAME_EN(2),
	COUNTRY_ISO(3),
	COUNTRY_OBSOLETE(4);
	
	public static final String SEPARATOR_SEMICOLON = ";";
	public static final String SEPARATOR_SPACE = " ";
	
	public final int INDEX;
	
	LineIndex(int index) {
		INDEX = index;
	}
	
}