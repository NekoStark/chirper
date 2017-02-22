package it.unifi.ing.chirper.dao;

import org.junit.Test;

import it.unifi.ing.chirper.dao.delegates.UserDaoDelegate;
import it.unifi.ing.chirper.test.persistence.JpaIT;
import it.unifi.ing.chirper.test.persistence.JpaTestInitializationException;

public class UserDaoJpaIT extends JpaIT{
private UserDaoDelegate userDaoTest;
	
	@Override
	protected void initTest() throws JpaTestInitializationException {
		userDaoTest = new UserDaoDelegate();

		try {
			userDaoTest.init(entityManager);
		} catch (IllegalAccessException e) {
			throw new JpaTestInitializationException(e);
		}
		
		userDaoTest.insertData(entityManager);
	}

	@Test
	public void testFindById() {
		userDaoTest.testFindById();
	}

	@Test
	public void testFindByUsername() {
		userDaoTest.testFindByUsername();
	}

	@Test
	public void testSave() {
		userDaoTest.testSave();
	}

	@Test
	public void testDeleteUser() {
		userDaoTest.testDeleteUser();
	}
}
