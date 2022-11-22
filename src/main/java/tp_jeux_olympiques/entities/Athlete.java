package tp_jeux_olympiques.entities;

import java.util.Collections;
import java.util.HashSet;
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
import tp_jeux_olympiques.enums.Sex;

@Entity
@Table(name = "athlete")
public class Athlete {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 100)
	public String name;
	
	@Column(name = "birth_year")
	public Integer birthYear;
	
	@Column
	public Float height;
	
	@Column
	public Float weight;
	
	@Column(nullable = false, length = 15)
	@Enumerated(value = EnumType.STRING)
	public Sex sex;
	
	@OneToMany(mappedBy = "athlete")
	private Set<Performance> performances = new HashSet<>();

	public Athlete() { }

	public Athlete(String name, Integer birthYear, Float height, Float weight, Sex sex) {
		this.name = name;
		this.birthYear = birthYear;
		this.height = height;
		this.weight = weight;
		this.sex = sex;
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
	 * Getter for {@link #sex}.
	 *
	 * @return
	 */
	public Sex getSex() {
		return sex;
	}

	/**
	 * Getter for {@link #performances}.
	 *
	 * @return
	 */
	public Set<Performance> getPerformances() {
		return Collections.unmodifiableSet(performances);
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
	 * Setter for {@link #sex}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	
}
