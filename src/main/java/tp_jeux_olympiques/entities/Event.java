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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import tp_jeux_olympiques.enums.Distinction;

@Entity
@Table(name = "event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	public String label;
	
	@Column(nullable = false, length = 15)
	@Enumerated(value = EnumType.STRING)
	public Distinction distinction;
	
	@ManyToOne
	@JoinColumn(name = "id_sport", nullable = false)
	private Sport sport;
	
	@OneToMany(mappedBy = "event")
	private Set<Performance> performances = new HashSet<>();
	
	@OneToOne
	@JoinColumn(name = "id_text_content")
	private TextContent textContent;

	public Event() { }

	public Event(String label, Distinction distinction, Sport sport) {
		this.label = label;
		this.distinction = distinction;
		this.sport = sport;
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
	 * Getter for {@link #label}.
	 *
	 * @return
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Getter for {@link #distinction}.
	 *
	 * @return
	 */
	public Distinction getDistinction() {
		return distinction;
	}

	/**
	 * Getter for {@link #sport}.
	 *
	 * @return
	 */
	public Sport getSport() {
		return sport;
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
	 * Getter for {@link #textContent}.
	 *
	 * @return
	 */
	public TextContent getTextContent() {
		return textContent;
	}

	/**
	 * Setter for {@link #label}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Setter for {@link #distinction}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setDistinction(Distinction distinction) {
		this.distinction = distinction;
	}

	/**
	 * Setter for {@link #sport}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setSport(Sport sport) {
		this.sport = sport;
	}

	/**
	 * Setter for {@link #textContent}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setTextContent(TextContent textContent) {
		this.textContent = textContent;
	}
	
}
