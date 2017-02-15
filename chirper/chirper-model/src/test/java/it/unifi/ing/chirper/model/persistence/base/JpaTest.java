package it.unifi.ing.chirper.model.persistence.base;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;

public abstract class JpaTest {

	protected static EntityManagerFactory entityManagerFactory;
	protected EntityManager entityManager;
	
	public static void initEntityManagerFactory( String persistenceUnit ) {
		entityManagerFactory = Persistence.createEntityManagerFactory( persistenceUnit );
	}
	 
	@Before 
	public void setUp() throws Exception {
		entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
        clearData();
        entityManager.getTransaction().commit();
        
		entityManager.getTransaction().begin();
        insertData();
        entityManager.getTransaction().commit();
        entityManager.clear();
		
        entityManager.getTransaction().begin();
	}
	
	@AfterClass
	public static void tearDownClass() {
		entityManagerFactory.close();
	}
	
	@After
	public void tearDown() {
		if ( entityManager.getTransaction().isActive() ) {
			entityManager.getTransaction().rollback();
		}
		entityManager.close();
	}
	
	protected void clearData() throws Exception {
		//entityManager.createNativeQuery( "TRUNCATE SCHEMA public AND COMMIT" ).executeUpdate();
	}

    protected abstract void insertData() throws Exception;
	
}
