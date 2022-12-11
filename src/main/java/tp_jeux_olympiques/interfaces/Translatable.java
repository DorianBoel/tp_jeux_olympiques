package tp_jeux_olympiques.interfaces;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;

import tp_jeux_olympiques.entities.Country;
import tp_jeux_olympiques.entities.Event;
import tp_jeux_olympiques.entities.Language;
import tp_jeux_olympiques.entities.Sport;
import tp_jeux_olympiques.entities.TextContent;
import tp_jeux_olympiques.entities.Translation;
import tp_jeux_olympiques.enums.LineIndex;

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
	
	public enum TranslatableType {
	
		COUNTRY(Country.class, Map.ofEntries(
				new AbstractMap.SimpleEntry<String, LineIndex>("en", LineIndex.COUNTRY_NAME_EN),
				new AbstractMap.SimpleEntry<String, LineIndex>("fr", LineIndex.COUNTRY_NAME_FR)
			)
		),
		EVENT(Event.class, Map.ofEntries(
				new AbstractMap.SimpleEntry<String, LineIndex>("en", LineIndex.EVENT_LABEL_EN),
				new AbstractMap.SimpleEntry<String, LineIndex>("fr", LineIndex.EVENT_LABEL_FR)
			)
		),
		SPORT(Sport.class, Map.ofEntries(
				new AbstractMap.SimpleEntry<String, LineIndex>("en", LineIndex.SPORT_LABEL_EN),
				new AbstractMap.SimpleEntry<String, LineIndex>("fr", LineIndex.SPORT_LABEL_FR)
			)
		);
		
		private Class<? extends Translatable> implementation;
		private Map<String, LineIndex> translationIndexes;
		
		private TranslatableType(Class<? extends Translatable> implementation,
				Map<String, LineIndex> translationIndexes)
		{
			this.implementation = implementation;
			this.translationIndexes = translationIndexes;
		}
		
		public Class<? extends Translatable> getImplementation() {
			return implementation;
		}
		
		public Map<String, LineIndex> getTranslationIndexes() {
			return Collections.unmodifiableMap(translationIndexes);
		}
		
	}
	
}
