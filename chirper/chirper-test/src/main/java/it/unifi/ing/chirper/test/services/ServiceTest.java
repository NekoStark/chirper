package it.unifi.ing.chirper.test.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;

public abstract class ServiceTest {

	protected EntityManagerFactory entityManagerFactory;
	protected EntityManager entityManager;

	@Before
	public void setUp() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory(getPersistenceUnitName());
		entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		initTest();
		entityManager.getTransaction().commit();
		entityManager.clear();

		entityManager.getTransaction().begin();
	}

	@After
	public void tearDown() {
		if (entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().rollback();
		}
		entityManager.close();
		entityManagerFactory.close();
	}

	protected abstract String getPersistenceUnitName();

	protected abstract void initTest() throws Exception;

}
