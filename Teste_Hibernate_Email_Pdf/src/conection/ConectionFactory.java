package conection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConectionFactory {
	
	private static EntityManagerFactory emfClienteTeste = Persistence.createEntityManagerFactory("projetoHibernate");
	
	public EntityManager getConection() {
		return emfClienteTeste.createEntityManager();
	}

}
