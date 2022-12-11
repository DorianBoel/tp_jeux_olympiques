package tp_jeux_olympiques.interfaces;

import tp_jeux_olympiques.entities.Language;
import tp_jeux_olympiques.entities.TextContent;
import tp_jeux_olympiques.entities.Translation;

public interface Translatable {

	TextContent getTextContent();
	
	void setTextContent(TextContent textContent);
	
	default String translateTo(Language language) {
		TextContent tc = getTextContent();
		if (tc.getLanguage().equals(language)) {
			return tc.getText();
		}
		Translation tl = tc.getTranslations().stream()
			.filter(t -> t.getLanguage().equals(language))
			.findAny().orElse(null);
		return tl != null ? tl.getValue() : null;
	};
	
}
