package tp_jeux_olympiques.entities;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "text_content")
public class TextContent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 100)
	private String text;

	@OneToMany(mappedBy = "textContent")
	private Set<Translation> translations = new HashSet<>();

	public TextContent() { }
	
	public TextContent(String text) {
		this.text = text;
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
	 * Getter for {@link #text}.
	 *
	 * @return
	 */
	public String getText() {
		return text;
	}

	/**
	 * Getter for {@link #translations}.
	 *
	 * @return
	 */
	public Set<Translation> getTranslations() {
		return Collections.unmodifiableSet(translations);
	}

	/**
	 * Setter for {@link #text}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setText(String text) {
		this.text = text;
	}
	
}
