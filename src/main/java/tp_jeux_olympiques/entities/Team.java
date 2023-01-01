package tp_jeux_olympiques.entities;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "team")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 75)
	private String name;
	
	@Column(name = "code_ioc", nullable = false, length = 3)
	private String codeIOC;
	
	@ManyToOne
	@JoinColumn(name = "id_country")
	private Country country;
	
	@OneToMany(mappedBy = "team")
	private Set<Performance> performances = new HashSet<>();
	
	public Team(String name, String codeIOC, Country country) {
		this.name = name;
		this.codeIOC = codeIOC;
		this.country = country;
		country.addTeam(this);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(codeIOC, country, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Team)) {
			return false;
		}
		Team other = (Team) obj;
		return Objects.equals(codeIOC, other.codeIOC) && Objects.equals(country, other.country)
				&& Objects.equals(name, other.name);
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
	 * Getter for {@link #codeIOC}.
	 *
	 * @return
	 */
	public String getCodeIOC() {
		return codeIOC;
	}

	/**
	 * Getter for {@link #country}.
	 *
	 * @return
	 */
	public Country getCountry() {
		return country;
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
	 * Setter for {@link #codeIOC}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setCodeIOC(String codeIOC) {
		this.codeIOC = codeIOC;
	}

	/**
	 * Setter for {@link #country}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setCountry(Country country) {
		this.country = country;
	}
	
}
