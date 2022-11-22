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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import tp_jeux_olympiques.enums.Season;

@Entity
@Table(name = "olympic_games_edition")
public class OlympicGamesEdition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private int year;
	
	@Column(nullable = false, length = 15)
	@Enumerated(value = EnumType.STRING)
	private Season season;
	
	@ManyToOne
	@JoinColumn(name = "id_city", nullable = false)
	private City city;
	
	@OneToMany(mappedBy = "olympicGamesEdition")
	private Set<Performance> performances = new HashSet<>();
	
	public OlympicGamesEdition() { }

	public OlympicGamesEdition(int year, Season season, City city) {
		this.year = year;
		this.season = season;
		this.city = city;
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
	 * Getter for {@link #year}.
	 *
	 * @return
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Getter for {@link #season}.
	 *
	 * @return
	 */
	public Season getSeason() {
		return season;
	}

	/**
	 * Getter for {@link #city}.
	 *
	 * @return
	 */
	public City getCity() {
		return city;
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
	 * Setter for {@link #year}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Setter for {@link #season}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setSeason(Season season) {
		this.season = season;
	}

	/**
	 * Setter for {@link #city}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setCity(City city) {
		this.city = city;
	}
	
}
