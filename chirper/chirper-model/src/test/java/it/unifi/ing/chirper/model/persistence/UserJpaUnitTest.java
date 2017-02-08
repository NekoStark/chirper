package it.unifi.ing.chirper.model.persistence;

import org.junit.Test;

import it.unifi.ing.chirper.model.persistence.base.JpaUnitTest;

public class UserJpaUnitTest extends JpaUnitTest {

	private UserJpaTest userTest;

	@Override
	public void insertData() {
		userTest.insertData();
	}

	@Test
	public void readTest() {
		userTest.readTest();
	}
	
	
	
}
