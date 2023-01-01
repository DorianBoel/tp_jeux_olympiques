package tp_jeux_olympiques.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
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
import tp_jeux_olympiques.interfaces.Translatable;

@Entity
@Table(name = "event")
public class Event implements Translatable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 15)
	@Enumerated(value = EnumType.STRING)
	private Distinction distinction;
	
	@ManyToOne
	@JoinColumn(name = "id_sport", nullable = false)
	private Sport sport;
	
	@OneToMany(mappedBy = "event")
	private Set<Performance> performances = new HashSet<>();
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_text_content")
	private TextContent textContent;
	
	public Event (TextContent textContent, Distinction distinction, Sport sport) {
		this.textContent = textContent;
		this.distinction = distinction;
		this.sport = sport;
		sport.addEvent(this);
	}

	public Event(String label, Language language, Distinction distinction, Sport sport) {
		this(new TextContent(label, language), distinction, sport);
	}

	public void addPerformance(Performance performance) {
		performances.add(performance);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(sport, textContent);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Event)) {
			return false;
		}
		Event other = (Event) obj;
		return Objects.equals(sport, other.sport)
				&& Objects.equals(textContent, other.textContent);
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
		return textContent.getText();
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
	public List<Performance> getPerformances() {
		return Collections.unmodifiableList(new ArrayList<>(performances));
	}
	
	/**
	 * Getter for {@link #textContent}.
	 *
	 * @return
	 */
	@Override
	public TextContent getTextContent() {
		return textContent;
	}

	/**
	 * Setter for {@link #label}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setLabel(String label) {
		textContent.setText(label);
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
	@Override
	public void setTextContent(TextContent textContent) {
		this.textContent = textContent;
	}
	
}
