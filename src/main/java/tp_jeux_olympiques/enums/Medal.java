package tp_jeux_olympiques.enums;

import jakarta.persistence.Entity;

@Entity
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
