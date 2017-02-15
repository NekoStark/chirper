package it.unifi.ing.chirper.model.persistence.delegates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;

import it.unifi.ing.chirper.model.Chirp;
import it.unifi.ing.chirper.model.User;
import it.unifi.ing.chirper.model.factory.ModelFactory;

public class ChirpJpaTestDelegate {

	private String uuid;
	
	public void insertData(EntityManager entityManager) {
	}

	public void readTest(EntityManager entityManager) {
	}
	
}
