package it.unifi.ing.chirper.dao;

import org.junit.Test;

import it.unifi.ing.chirper.dao.delegates.ChirpDaoTestDelegate;
import it.unifi.ing.chirper.test.persistence.JpaIT;
import it.unifi.ing.chirper.test.persistence.JpaTestInitializationException;

public class ChirpDaoJpaIT extends JpaIT{
private ChirpDaoTestDelegate chirpDaoTest;
	
	@Override
	protected void initTest() throws JpaTestInitializationException {
		chirpDaoTest = new ChirpDaoTestDelegate();


		try {
			chirpDaoTest.init(entityManager);
		} catch (IllegalAccessException e) {
			throw new JpaTestInitializationException(e);
		}
		
		chirpDaoTest.insertData(entityManager);
	}

	@Test
	public void testFindById() {
		chirpDaoTest.testFindById();
	}

	
	@Test
	public void testFindByAuthor() {
		chirpDaoTest.testFindByAuthor();
	}

	
	@Test
	public void testFindByReference() {
		chirpDaoTest.testFindByReference();
	}
	
	
	@Test
	public void testDelete() {
		chirpDaoTest.testDelete();
	}

	@Test
	public void testSave() {
		chirpDaoTest.testSave();
	}

}
