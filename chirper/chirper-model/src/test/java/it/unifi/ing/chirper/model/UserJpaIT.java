package it.unifi.ing.chirper.model;


import org.junit.Test;

import it.unifi.ing.chirper.model.delegates.UserJpaTestDelegate;
import it.unifi.ing.chirper.test.persistence.JpaIT;

public class UserJpaIT extends JpaIT {

	private UserJpaTestDelegate userTest;

	@Override
	public void initTest() {
		userTest = new UserJpaTestDelegate();
		userTest.init(entityManager);
		userTest.insertData();
	}

	@Test
	public void readTest() {
		userTest.readTest();
	}

}
