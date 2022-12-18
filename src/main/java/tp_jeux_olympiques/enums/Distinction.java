package tp_jeux_olympiques.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum Distinction {

	MENS("Men's", Gender.MALE),
	WOMENS("Women's", Gender.FEMALE),
	MIXED("Mixed", Gender.FEMALE, Gender.MALE);
	
	private String label;
	
	private Set<Gender> genders = new HashSet<>();
	
	private Distinction(String label, Gender... genders) {
		this.label = label;
		this.genders.addAll(Arrays.asList(genders));
	}

	/**
	 * Getter for {@link #label}.
	 *
	 * @return
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Getter for {@link #genders}.
	 *
	 * @return
	 */
	public Set<Gender> getgenders() {
		return Collections.unmodifiableSet(genders);
	}
	
}
