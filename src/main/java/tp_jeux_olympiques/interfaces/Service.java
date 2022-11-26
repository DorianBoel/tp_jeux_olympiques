package tp_jeux_olympiques.interfaces;

import java.util.Set;

public interface Service<T> {

	T register(T entity);
	
	Set<T> getRegistered();
	
}
