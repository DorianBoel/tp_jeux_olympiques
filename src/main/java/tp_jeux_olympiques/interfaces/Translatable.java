package tp_jeux_olympiques.interfaces;

import tp_jeux_olympiques.entities.Country;
import tp_jeux_olympiques.entities.Event;
import tp_jeux_olympiques.entities.Language;
import tp_jeux_olympiques.entities.Sport;
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

	public static enum TranslatableType {
		
		COUNTRY(Country.class),
		EVENT(Event.class),
		SPORT(Sport.class);
		
		public Class<? extends Translatable> implementation;
		
		private TranslatableType(Class<? extends Translatable> implementation) {
			this.implementation = implementation;
		}
		
	}
	
}
