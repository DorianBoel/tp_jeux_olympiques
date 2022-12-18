package tp_jeux_olympiques.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import tp_jeux_olympiques.enums.LanguageISOCode;

@Entity
@Table(name = "language")
public class Language {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(name = "code_iso", nullable = false, unique = true, length = 2)
	@Convert(converter = LanguageISOCode.Converter.class)
	private LanguageISOCode isoCode;
	
	public Language() { }
	
	public Language(String name, LanguageISOCode isoCode) {
		this.name = name;
		this.isoCode = isoCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(isoCode, name);
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
		return Objects.equals(isoCode, other.isoCode) && Objects.equals(name, other.name);
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
	 * Getter for {@link #isoCode}.
	 *
	 * @return
	 */
	public LanguageISOCode getISOCode() {
		return isoCode;
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
	public void setISOCode(LanguageISOCode isoCode) {
		this.isoCode = isoCode;
	}
	
}
