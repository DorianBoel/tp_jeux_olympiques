package tp_jeux_olympiques.entities;

import java.util.Collections;
import java.util.HashSet;
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
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(name = "code_ioc", nullable = false, unique = true, length = 3)
	private String codeIOC;
	
	@ManyToOne
	@JoinColumn(name = "id_country")
	private Country country;
	
	@OneToMany(mappedBy = "team")
	private Set<Performance> performances = new HashSet<>();

	public Team() { }

	public Team(String name, String codeIOC) {
		this.name = name;
		this.codeIOC = codeIOC;
	}
	
	public Team(String name, String codeIOC, Country country) {
		this(name, codeIOC);
		this.country = country;
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
