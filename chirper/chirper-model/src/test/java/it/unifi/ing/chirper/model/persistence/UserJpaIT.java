package it.unifi.ing.chirper.model.persistence;


import org.junit.Test;

import it.unifi.ing.chirper.model.persistence.base.JpaIT;
import it.unifi.ing.chirper.model.persistence.delegates.UserJpaTestDelegate;

public class UserJpaIT extends JpaIT {

	private UserJpaTestDelegate userTest;

	@Override
	public void insertData() {
		userTest = new UserJpaTestDelegate();
		userTest.insertData(entityManager);
	}

	@Test
	public void readTest() {
		userTest.readTest(entityManager);
	}

}
