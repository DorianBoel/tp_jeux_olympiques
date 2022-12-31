package tp_jeux_olympiques.enums;

import java.util.Arrays;
import java.util.Objects;

import jakarta.persistence.AttributeConverter;

public enum LanguageISOCode {

	ENGLISH("en"),
	FRENCH("fr");
	
	private String isoCode;
	
	private LanguageISOCode(String isoCode) {
		this.isoCode = isoCode;
	}
	
	public String getCode() {
		return isoCode;
	}
	
	public static class Converter implements AttributeConverter<LanguageISOCode, String> {

		@Override
		public String convertToDatabaseColumn(LanguageISOCode attribute) {
			if (attribute == null) {
				return null;
			}
			return attribute.getCode();
		}

		@Override
		public LanguageISOCode convertToEntityAttribute(String dbData) {
			return Arrays.asList(LanguageISOCode.values()).stream()
				.filter(o -> Objects.equals(o.getCode(), dbData))
				.findAny().orElse(null);
		}
		
	}
	
}
