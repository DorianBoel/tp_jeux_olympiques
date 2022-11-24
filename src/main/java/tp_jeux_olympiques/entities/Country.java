package tp_jeux_olympiques.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import tp_jeux_olympiques.LanguageRepository;
import tp_jeux_olympiques.LineIndex;
import tp_jeux_olympiques.interfaces.Translatable;

@Entity
@Table(name = "country")
public class Country implements Translatable {
	
	private static LanguageRepository langRepository = LanguageRepository.getInstance();
	
	private static Map<Language, LineIndex> translationIndexes = new HashMap<>();
	
	static {
		translationIndexes.put(langRepository.get("fr"), LineIndex.COUNTRY_NAME_FR);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "code_iso", unique = true, length = 3)
	private String codeISO;
	
	@Column(nullable = false)
	private boolean obsolete;
	
	@OneToMany(mappedBy = "country")
	private Set<Team> teams = new HashSet<>();
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_text_content")
	private TextContent textContent;

	public Country() { }
	
	public Country(String name, Language language, String codeISO, boolean obsolete) {
		this.textContent = new TextContent(name, language);
		this.obsolete = obsolete;
		this.codeISO = codeISO;
	}

	public void addTeam(Team team) {
		this.teams.add(team);
	}
	
	@Override
	public String translate(Language language) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(codeISO, obsolete, textContent);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Country)) {
			return false;
		}
		Country other = (Country) obj;
		return Objects.equals(codeISO, other.codeISO) && obsolete == other.obsolete
				&& Objects.equals(textContent, other.textContent);
	}

	/**
	 * Getter for {@link #name}.
	 *
	 * @return
	 */
	public String getName() {
		return textContent.getText();
	}

	/**
	 * Getter for {@link #codeISO}.
	 *
	 * @return
	 */
	public String getCodeISO() {
		return codeISO;
	}

	/**
	 * Getter for {@link #obsolete}.
	 *
	 * @return
	 */
	public boolean isObsolete() {
		return obsolete;
	}

	/**
	 * Getter for {@link #teams}.
	 *
	 * @return
	 */
	public List<Team> getTeams() {
		return Collections.unmodifiableList(new ArrayList<>(teams));
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
	 * Setter for {@link #name}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setName(String name) {
		textContent.setText(name);
	}

	/**
	 * Setter for {@link #codeISO}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setCodeISO(String codeISO) {
		this.codeISO = codeISO;
	}

	/**
	 * Setter for {@link #obsolete}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setObsolete(boolean obsolete) {
		this.obsolete = obsolete;
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

	@Override
	public LineIndex getTranslationIndex(Language language) {
		return translationIndexes.get(language);
	}

}
