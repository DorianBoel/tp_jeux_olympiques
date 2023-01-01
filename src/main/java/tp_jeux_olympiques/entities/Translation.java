package tp_jeux_olympiques.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "translation")
public class Translation {

	@EmbeddedId
	private TranslationKey id;
	
	@Column
	private String value;
	
	@ManyToOne
	@MapsId(value = "idLanguage")
	@JoinColumn(name = "id_language")
	private Language language;
	
	@ManyToOne
	@MapsId(value = "idTextContent")
	@JoinColumn(name = "id_text_content")
	private TextContent textContent;

	public Translation(String value, Language language, TextContent textContent) {
		this.value = value;
		this.language = language;
		if (textContent != null) {			
			this.textContent = textContent;
			textContent.addTranslation(this);
			id = new TranslationKey(language.getId(), textContent.getId());
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, language, textContent, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Translation)) {
			return false;
		}
		Translation other = (Translation) obj;
		return Objects.equals(id, other.id) && Objects.equals(language, other.language)
				&& Objects.equals(textContent, other.textContent) && Objects.equals(value, other.value);
	}

	/**
	 * Getter for {@link #id}.
	 *
	 * @return
	 */
	public TranslationKey getId() {
		return id;
	}

	/**
	 * Getter for {@link #value}.
	 *
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Getter for {@link #language}.
	 *
	 * @return
	 */
	public Language getLanguage() {
		return language;
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
	 * Setter for {@link #value}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Setter for {@link #language}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setLanguage(Language language) {
		this.language = language;
	}

	/**
	 * Setter for {@link #textContent}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setTextContent(TextContent textContent) {
		this.textContent = textContent;
	}

	@Embeddable
	public static class TranslationKey implements Serializable {
		
	    private static final long serialVersionUID = 1L;

		@Column
	    private int idLanguage;
	    
	    @Column
	    private int idTextContent;
	    
	    public TranslationKey(int idLanguage, int idTextContent) {
	    	this.idLanguage = idLanguage;
	    	this.idTextContent = idTextContent;
	    }
	    
		@Override
		public int hashCode() {
			return Objects.hash(idLanguage, idTextContent);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (!(obj instanceof TranslationKey)) {
				return false;
			}
			TranslationKey other = (TranslationKey) obj;
			return idLanguage == other.idLanguage && idTextContent == other.idTextContent;
		}

		/**
		 * Getter for {@link #idLanguage}.
		 *
		 * @return
		 */
		public int getIdLanguage() {
			return idLanguage;
		}

		/**
		 * Getter for {@link #idTextContent}.
		 *
		 * @return
		 */
		public int getIdTextContent() {
			return idTextContent;
		}

		/**
		 * Setter for {@link #idLanguage}.
		 *
		 * @param The new ATTRIBUTE to replace the current one
		 */
		public void setIdLanguage(int idLanguage) {
			this.idLanguage = idLanguage;
		}

		/**
		 * Setter for {@link #idTextContent}.
		 *
		 * @param The new ATTRIBUTE to replace the current one
		 */
		public void setIdTextContent(int idTextContent) {
			this.idTextContent = idTextContent;
		}

	}
	
}
