package it.unifi.ing.chirper.model.persistence.base;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;

public abstract class JpaTest {

	protected EntityManagerFactory entityManagerFactory;
	protected EntityManager entityManager;
	
	@Before 
	public void setUp() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory( getPersistenceUnitName() );
		entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
        insertData();
        entityManager.getTransaction().commit();
        entityManager.clear();
		
        entityManager.getTransaction().begin();
	}
	
	@After
	public void tearDown() {
		if ( entityManager.getTransaction().isActive() ) {
			entityManager.getTransaction().rollback();
		}
		entityManager.close();
		entityManagerFactory.close();
	}
	
	protected abstract String getPersistenceUnitName();
    protected abstract void insertData() throws Exception;
	
}
