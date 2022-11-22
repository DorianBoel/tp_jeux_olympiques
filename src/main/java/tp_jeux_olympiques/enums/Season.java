package tp_jeux_olympiques.enums;

public enum Season {

	SUMMER("summer"),
	WINTER("winter");
	
	private String label;
	
	private Season(String label) {
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
