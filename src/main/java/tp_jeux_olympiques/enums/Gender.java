package tp_jeux_olympiques.enums;

public enum Gender {

	FEMALE("F"),
	MALE("M");
	
	private String label;
	
	private Gender(String label) {
		this.label = label;
	}

	/**
	 * Getter for {@link #label}.
	 *
	 * @return
	 */
	public String getLabel() {
		return label;
	}
	
}
