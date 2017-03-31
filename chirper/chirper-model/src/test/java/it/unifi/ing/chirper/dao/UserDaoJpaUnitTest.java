package it.unifi.ing.chirper.dao;

import org.junit.Test;

import it.unifi.ing.chirper.dao.delegates.UserDaoTestDelegate;
import it.unifi.ing.chirper.test.exception.TestInitializationException;
import it.unifi.ing.chirper.test.persistence.JpaUnitTest;

public class UserDaoJpaUnitTest extends JpaUnitTest {

	private UserDaoTestDelegate userDaoTest;
	
	@Override
	protected void initTest() throws TestInitializationException {
		userDaoTest = new UserDaoTestDelegate();
    
		try {
			userDaoTest.init(entityManager);
		} catch (IllegalAccessException e) {
			throw new TestInitializationException(e);
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
	
	
	@Test
	public void testAllUser() {
		userDaoTest.testAllUser();
	}

}
