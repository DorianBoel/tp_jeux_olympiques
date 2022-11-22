package tp_jeux_olympiques.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "language")
public class Language {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(nullable = false, unique = true, length = 2)
	private String codeISO;
	
	public Language() { }
	
	public Language(String name, String codeIso) {
		this.name = name;
		this.codeISO = codeIso;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codeISO, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Language)) {
			return false;
		}
		Language other = (Language) obj;
		return Objects.equals(codeISO, other.codeISO) && Objects.equals(name, other.name);
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
	
}
