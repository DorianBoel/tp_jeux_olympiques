package tp_jeux_olympiques.entities;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import tp_jeux_olympiques.interfaces.Translatable;

@Entity
@Table(name = "sport")
public class Sport implements Translatable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy = "sport")
	private Set<Event> events = new HashSet<>();
	
	@OneToOne
	@JoinColumn(name = "id_text_content")
	private TextContent textContent;
	
	public Sport() { }

	public Sport(String label, Language language) {
		textContent = new TextContent(label, language);
	}

	public void addEvent(Event event) {
		events.add(event);
	}
	
	@Override
	public String translate(Language language) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(textContent);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Sport)) {
			return false;
		}
		Sport other = (Sport) obj;
		return Objects.equals(textContent, other.textContent);
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
	 * Setter for {@link #textContent}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	@Override
	public void setTextContent(TextContent textContent) {
		this.textContent = textContent;
	}

}
