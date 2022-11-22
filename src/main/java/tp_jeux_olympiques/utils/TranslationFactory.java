package tp_jeux_olympiques.utils;

import tp_jeux_olympiques.entities.Language;
import tp_jeux_olympiques.entities.Translation;
import tp_jeux_olympiques.interfaces.Translatable;

public class TranslationFactory {

	public TranslationFactory() {
		
	}
	
	public Translation createTranslation(Translatable object, Language language, String text) {
		return new Translation(text, language, object.getTextContent());
	}
	
}
