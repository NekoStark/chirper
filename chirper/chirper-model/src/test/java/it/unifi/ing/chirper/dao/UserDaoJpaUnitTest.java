package it.unifi.ing.chirper.dao;

import org.junit.Before;
import org.junit.Test;

import it.unifi.ing.chirper.dao.delegate.UserDaoDelegate;
import it.unifi.ing.chirper.model.persistence.base.JpaUnitTest;

public class UserDaoJpaUnitTest extends JpaUnitTest{

	private UserDaoDelegate userDaoTest;
	
	@Before
	public void setup() throws Exception{
		userDaoTest.setUp(entityManager);
	}
	
	@Override
	protected void insertData() throws Exception {
		
		userDaoTest = new UserDaoDelegate();
		
		userDaoTest.insertData(entityManager);
		
	}
	
	
	@Test
	public void readTest() {
		userDaoTest.testSave();
		userDaoTest.testFindById();
		userDaoTest.testFindByUsername();
		userDaoTest.testDeleteUser();
	}
	
	

}
