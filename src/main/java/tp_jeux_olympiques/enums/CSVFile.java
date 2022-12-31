package tp_jeux_olympiques.enums;

public enum CSVFile {
	
	SPORTS_LIST("liste_des_sports"),
	EVENTS_LIST("liste_des_epreuves"),
	COUNTRY_CODES("wikipedia-iso-country-codes"),
	PERFORMANCES("athlete_epreuves");
	
	private static final String extension = ".csv";
	
	private String fileName;
	
	private CSVFile(String fileName) {
		this.fileName = fileName + extension;
	}

	/**
	 * Getter for {@link #fileName}.
	 *
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}
		
}