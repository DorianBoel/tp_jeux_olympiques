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
@Table(name = "country")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(name = "code_iso", unique = true, length = 3)
	private String codeISO;
	
	@Column(nullable = false)
	private boolean obsolete;
	
	@OneToMany(mappedBy = "country")
	private Set<Team> teams = new HashSet<>();
	
	@OneToOne
	@JoinColumn(name = "id_text_content")
	private TextContent textContent;

	public Country() { }

	public Country(String name, boolean obsolete) {
		this.name = name;
		this.obsolete = obsolete;
	}
	
	public Country(String name, String codeISO, boolean obsolete) {
		this(name, obsolete);
		this.codeISO = codeISO;
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
	public Set<Team> getTeams() {
		return Collections.unmodifiableSet(teams);
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
	 * Setter for {@link #name}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setName(String name) {
		this.name = name;
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
	public void setTextContent(TextContent textContent) {
		this.textContent = textContent;
	}

}
