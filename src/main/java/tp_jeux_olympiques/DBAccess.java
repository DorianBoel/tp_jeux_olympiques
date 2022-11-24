package tp_jeux_olympiques;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class DBAccess {

	private static final String JPA_SCHEMA = "cda_tp_jpa_jeux_olympiques";
	
	private static DBAccess instance;
	
	private EntityManagerFactory emFactory;
	
	private DBAccess() {
		emFactory = Persistence.createEntityManagerFactory(JPA_SCHEMA);
	}
	
	public static DBAccess getInstance() {
		if (instance == null) {
			instance = new DBAccess();
		}
		return instance;
	}
	
	public EntityManagerFactory getEmFactory() {
		return emFactory;
	}
	
}