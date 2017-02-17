package it.unifi.ing.chirper.model;

import org.junit.Test;

import it.unifi.ing.chirper.model.delegates.ChirpJpaTestDelegate;
import it.unifi.ing.chirper.test.persistence.JpaIT;

public class ChirpJpaIT extends JpaIT {

	private ChirpJpaTestDelegate chirpTest;

	@Override
	public void initTest() {
		chirpTest = new ChirpJpaTestDelegate();
		chirpTest.init(entityManager);
		chirpTest.insertData();
	}

	@Test
	public void readTest() {
		chirpTest.readTest();
	}



}