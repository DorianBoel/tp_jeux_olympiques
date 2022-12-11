package tp_jeux_olympiques.general;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tp_jeux_olympiques.enums.CSVFile;

public final class FileAccess {
	
	private static FileAccess singletonInstance;
	
	private static Path basePath = Paths.get("src/main/resources/data/csv/");
	
	public static final FileAccess getInstance() {
		if (singletonInstance == null) {			
			singletonInstance = new FileAccess();
		}
		return singletonInstance;
	}
	
	private Map<CSVFile, Path> filePaths = new HashMap<>();
	
	private FileAccess() {
		for (CSVFile key : CSVFile.values()) {
			String uri = String.format("%s/%s", basePath, key.getFileName());
			filePaths.put(key, Paths.get(uri));
		}
	}
	
	public List<String> getLines(CSVFile fileName) throws FileNotFoundException {
		
		Path filePath = filePaths.get(fileName);
		
		try {
			
			return Files.readAllLines(filePath, StandardCharsets.UTF_8);
			
		} catch(IOException err) {
			
			String message =  String.format("Fichier introuvable: %s", filePath);
			throw new FileNotFoundException(message);
			
		}
		
	}
	
}
