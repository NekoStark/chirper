package it.unifi.ing.chirper.dao;

import org.junit.Test;

import it.unifi.ing.chirper.dao.delegates.UserDaoTestDelegate;
import it.unifi.ing.chirper.test.persistence.JpaUnitTest;

public class UserDaoJpaUnitTest extends JpaUnitTest {

	private UserDaoTestDelegate userDaoTest;
	
	@Override
	protected void initTest() throws Exception {
		userDaoTest = new UserDaoTestDelegate();

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
