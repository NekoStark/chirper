package it.unifi.ing.chirper.model.persistence;

import org.junit.Test;

import it.unifi.ing.chirper.model.persistence.base.JpaIT;
import it.unifi.ing.chirper.model.persistence.delegates.ChirpJpaTestDelegate;

public class ChirpJpaIT extends JpaIT {

	private ChirpJpaTestDelegate chirpTest;

	@Override
	public void initTest() {
		chirpTest = new ChirpJpaTestDelegate();
		chirpTest.insertData(entityManager);
	}

	@Test
	public void readTest() {
		chirpTest.readTest(entityManager);
	}



}