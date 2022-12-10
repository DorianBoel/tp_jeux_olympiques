package tp_jeux_olympiques;

import java.io.FileNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class TpJeuxOlympiques {

	public static void main(String[] args) {
		
		DBAccess dbAccess = DBAccess.getInstance();
		EntityManagerFactory emFactory = dbAccess.getEmFactory();
		EntityManager entityManager = emFactory.createEntityManager();
		
		DataLoader dataLoader = new DataLoader(entityManager);
		
		entityManager.getTransaction().begin();
		
		try {
			dataLoader.populate();
		} catch (FileNotFoundException err) {
			System.err.println(err.getMessage());
		} finally {
			entityManager.close();
			emFactory.close();
		}
		
	}

}
