package tp_jeux_olympiques.entities;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
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
@Table(name = "text_content")
public class TextContent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 100)
	private String text;
	
	@ManyToOne
	@JoinColumn(name = "id_language")
	private Language language;

	@OneToMany(mappedBy = "textContent", cascade = CascadeType.MERGE)
	private Set<Translation> translations = new HashSet<>();

	public TextContent() { }
	
	public TextContent(String text, Language language) {
		this.text = text;
		this.language = language;
	}

	public void addTranslation(Translation translation) {
		translations.add(translation);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(language, text);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TextContent)) {
			return false;
		}
		TextContent other = (TextContent) obj;
		return Objects.equals(language, other.language) && Objects.equals(text, other.text);
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
	 * Getter for {@link #text}.
	 *
	 * @return
	 */
	public Language getLanguage() {
		return language;
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
	
	/**
	 * Setter for {@link #language}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setLanguage(Language language) {
		this.language = language;
	}
	
}
