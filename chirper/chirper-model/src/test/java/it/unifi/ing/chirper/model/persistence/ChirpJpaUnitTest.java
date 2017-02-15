package it.unifi.ing.chirper.model.persistence;

import org.junit.Test;

import it.unifi.ing.chirper.model.persistence.base.JpaUnitTest;
import it.unifi.ing.chirper.model.persistence.delegates.ChirpJpaTestDelegate;

public class ChirpJpaUnitTest extends JpaUnitTest{

	private ChirpJpaTestDelegate chirpTest;

	@Override
	public void insertData() {
		chirpTest = new ChirpJpaTestDelegate();
		chirpTest.insertData(entityManager);
	}

	@Test
	public void readTest() {
		chirpTest.readTest(entityManager);
	}
	

}
