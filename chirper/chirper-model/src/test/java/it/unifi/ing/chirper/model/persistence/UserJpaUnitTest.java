package it.unifi.ing.chirper.model.persistence;

import org.junit.Test;

import it.unifi.ing.chirper.model.persistence.base.JpaUnitTest;
import it.unifi.ing.chirper.model.persistence.delegates.UserJpaTestDelegate;

public class UserJpaUnitTest extends JpaUnitTest {

	private UserJpaTestDelegate userTest;

	@Override
	public void initTest() {
		userTest = new UserJpaTestDelegate();
		userTest.insertData(entityManager);
	}

	@Test
	public void readTest() {
		userTest.readTest(entityManager);
	}
	
}
