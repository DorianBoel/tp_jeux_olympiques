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
import tp_jeux_olympiques.enums.LanguageISOCode;
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
			new AbstractMap.SimpleEntry<LanguageISOCode, LineIndex>(LanguageISOCode.ENGLISH, LineIndex.COUNTRY_NAME_EN),
			new AbstractMap.SimpleEntry<LanguageISOCode, LineIndex>(LanguageISOCode.FRENCH, LineIndex.COUNTRY_NAME_FR)
		)),
		EVENT(Event.class, Map.ofEntries(
			new AbstractMap.SimpleEntry<LanguageISOCode, LineIndex>(LanguageISOCode.ENGLISH, LineIndex.EVENT_LABEL_EN),
			new AbstractMap.SimpleEntry<LanguageISOCode, LineIndex>(LanguageISOCode.FRENCH, LineIndex.EVENT_LABEL_FR)
		)),
		SPORT(Sport.class, Map.ofEntries(
			new AbstractMap.SimpleEntry<LanguageISOCode, LineIndex>(LanguageISOCode.ENGLISH, LineIndex.SPORT_LABEL_EN),
			new AbstractMap.SimpleEntry<LanguageISOCode, LineIndex>(LanguageISOCode.FRENCH, LineIndex.SPORT_LABEL_FR)
		));
		
		private Class<? extends Translatable> implementation;
		private Map<LanguageISOCode, LineIndex> translationIndexes;
		
		private TranslatableType(Class<? extends Translatable> implementation,
				Map<LanguageISOCode, LineIndex> translationIndexes)
		{
			this.implementation = implementation;
			this.translationIndexes = translationIndexes;
		}
		
		public Class<? extends Translatable> getImplementation() {
			return implementation;
		}
		
		public Map<LanguageISOCode, LineIndex> getTranslationIndexes() {
			return Collections.unmodifiableMap(translationIndexes);
		}
		
	}
	
}
