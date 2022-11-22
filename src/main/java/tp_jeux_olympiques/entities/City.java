package tp_jeux_olympiques.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "city")
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@OneToMany(mappedBy = "city")
	private Set<OlympicGamesEdition> olympicGamesEditions = new HashSet<>();

	public City() { }

	public City(String name) {
		this.name = name;
	}
	
	public void addEdition(OlympicGamesEdition olympicGamesEdition) {
		olympicGamesEditions.add(olympicGamesEdition);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof City)) {
			return false;
		}
		City other = (City) obj;
		return Objects.equals(name, other.name);
	}

	/**
	 * Getter for {@link #id}.
	 *
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Getter for {@link #name}.
	 *
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for {@link #olympicGamesEditions}.
	 *
	 * @return
	 */
	public List<OlympicGamesEdition> getOlympicGamesEditions() {
		return Collections.unmodifiableList(new ArrayList<>(olympicGamesEditions));
	}

	/**
	 * Setter for {@link #name}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
