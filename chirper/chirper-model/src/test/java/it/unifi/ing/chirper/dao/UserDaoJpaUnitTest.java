package it.unifi.ing.chirper.dao;

import org.junit.Test;

import it.unifi.ing.chirper.dao.delegates.UserDaoDelegate;
import it.unifi.ing.chirper.test.persistence.JpaUnitTest;

public class UserDaoJpaUnitTest extends JpaUnitTest {

	private UserDaoDelegate userDaoTest;
	
	@Override
	protected void initTest() throws Exception {
		userDaoTest = new UserDaoDelegate();

		userDaoTest.init(entityManager);
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
