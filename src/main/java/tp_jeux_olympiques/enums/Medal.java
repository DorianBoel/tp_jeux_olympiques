package tp_jeux_olympiques.enums;

public enum Medal {

	GOLD(1),
	SILVER(2),
	BRONZE(3);
	
	private int place;
	
	private Medal(int place) {
		this.place = place;
	}

	/**
	 * Getter for {@link #place}.
	 *
	 * @return
	 */
	public int getPlace() {
		return place;
	}
	
}
