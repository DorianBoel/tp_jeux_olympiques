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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sport")
public class Sport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 50)
	public String label;
	
	@OneToMany(mappedBy = "sport")
	private Set<Event> events = new HashSet<>();
	
	@OneToOne
	@JoinColumn(name = "id_text_content")
	private TextContent textContent;
	
	public Sport() { }

	public Sport(String label) {
		this.label = label;
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
	 * Getter for {@link #events}.
	 *
	 * @return
	 */
	public Set<Event> getEvents() {
		return Collections.unmodifiableSet(events);
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
	 * Setter for {@link #textContent}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setTextContent(TextContent textContent) {
		this.textContent = textContent;
	}
	
}
