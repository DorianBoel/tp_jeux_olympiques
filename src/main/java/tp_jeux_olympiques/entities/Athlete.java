package tp_jeux_olympiques.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import tp_jeux_olympiques.enums.Gender;

@Entity
@Table(name = "athlete")
public class Athlete {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 150)
	private String name;
	
	@Column(name = "birth_year")
	private Integer birthYear;
	
	@Column
	private Float height;
	
	@Column
	private Float weight;
	
	@Column(nullable = false, length = 15)
	@Enumerated(value = EnumType.STRING)
	public Gender gender;
	
	@OneToMany(mappedBy = "athlete")
	private Set<Performance> performances = new HashSet<>();

	public Athlete(String name, Integer birthYear, Float height, Float weight, Gender gender) {
		this.name = name;
		this.birthYear = birthYear;
		this.height = height;
		this.weight = weight;
		this.gender = gender;
	}
	
	public void addPerformance (Performance performance) {
		performances.add(performance);
	}

	@Override
	public int hashCode() {
		return Objects.hash(height, name, gender, weight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Athlete)) {
			return false;
		}
		Athlete other = (Athlete) obj;
		return Objects.equals(height, other.height) && Objects.equals(name, other.name)
				&& gender == other.gender && Objects.equals(weight, other.weight);
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
	 * Getter for {@link #birthYear}.
	 *
	 * @return
	 */
	public Integer getBirthYear() {
		return birthYear;
	}

	/**
	 * Getter for {@link #height}.
	 *
	 * @return
	 */
	public Float getHeight() {
		return height;
	}

	/**
	 * Getter for {@link #weight}.
	 *
	 * @return
	 */
	public Float getWeight() {
		return weight;
	}

	/**
	 * Getter for {@link #gender}.
	 *
	 * @return
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * Getter for {@link #performances}.
	 *
	 * @return
	 */
	public List<Performance> getPerformances() {
		return Collections.unmodifiableList(new ArrayList<>(performances));
	}

	/**
	 * Setter for {@link #name}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Setter for {@link #birthYear}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
	}

	/**
	 * Setter for {@link #height}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setHeight(Float height) {
		this.height = height;
	}

	/**
	 * Setter for {@link #weight}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setWeight(Float weight) {
		this.weight = weight;
	}

	/**
	 * Setter for {@link #gender}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
}
