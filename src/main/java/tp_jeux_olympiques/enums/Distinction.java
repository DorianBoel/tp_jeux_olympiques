package tp_jeux_olympiques.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum Distinction {

	MENS("Men's", Sex.MALE),
	WOMENS("Women's", Sex.FEMALE),
	MIXED("Mixed", Sex.FEMALE, Sex.MALE);
	
	private String label;
	
	private Set<Sex> sexes = new HashSet<>();
	
	private Distinction(String label, Sex... sexes) {
		this.label = label;
		this.sexes.addAll(Arrays.asList(sexes));
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
	 * Getter for {@link #sexes}.
	 *
	 * @return
	 */
	public Set<Sex> getSexes() {
		return Collections.unmodifiableSet(sexes);
	}
	
	
}
