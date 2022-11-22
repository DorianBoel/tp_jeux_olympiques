package tp_jeux_olympiques.enums;

public enum Sex {

	FEMALE("F"),
	MALE("M");
	
	private String label;
	
	private Sex(String label) {
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
