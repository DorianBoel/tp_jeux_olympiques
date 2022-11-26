package tp_jeux_olympiques.interfaces;

import tp_jeux_olympiques.entities.Language;
import tp_jeux_olympiques.entities.TextContent;

public interface TranslatableService<T extends Translatable> extends Service<T> {

	default TextContent createTextContent(String text, Language language) {
		return new TextContent(text, language);
	}
	
}
