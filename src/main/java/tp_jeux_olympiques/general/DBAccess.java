package tp_jeux_olympiques.general;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class DBAccess {

	private static String jpaSchema = "cda_tp_jpa_jeux_olympiques";
	
	private static DBAccess instance;
	
	public static DBAccess getInstance() {
		if (instance == null) {
			instance = new DBAccess();
		}
		return instance;
	}
	
	private EntityManagerFactory emFactory;
	
	private DBAccess() {
		emFactory = Persistence.createEntityManagerFactory(jpaSchema);
	}
	
	public EntityManagerFactory getEmFactory() {
		return emFactory;
	}
	
}