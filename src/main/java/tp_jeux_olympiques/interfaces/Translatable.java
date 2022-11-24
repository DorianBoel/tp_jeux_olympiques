package tp_jeux_olympiques.interfaces;

import tp_jeux_olympiques.LineIndex;
import tp_jeux_olympiques.entities.Language;
import tp_jeux_olympiques.entities.TextContent;

public interface Translatable {

	TextContent getTextContent();
	
	void setTextContent(TextContent textContent);
	
	String translate(Language language);
	
	LineIndex getTranslationIndex(Language language);
	
}
