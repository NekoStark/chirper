package it.unifi.ing.chirper.model.delegates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;

import it.unifi.ing.chirper.model.Chirp;
import it.unifi.ing.chirper.model.User;
import it.unifi.ing.chirper.model.factory.ModelFactory;

public class UserJpaTestDelegate {

	private EntityManager entityManager;

	private String uuid;
	
	public void init(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void insertData() {
		User user = ModelFactory.user();
		user.setEmail("prova@prova.it");
		user.setPassword("password");
		user.setUserName("user");

		User friend = ModelFactory.user();
		user.addFriend(friend);
		
		Chirp chirp = ModelFactory.chirp();
		chirp.setAuthor(user);
		
		entityManager.persist(user);
		entityManager.persist(friend);
		entityManager.persist(chirp);

		uuid = user.getUuid();
	}

	public void readTest() {
		User result = entityManager
				.createQuery("from User "
						+ "where uuid = :uuid", User.class)
				.setParameter("uuid", uuid)
				.getSingleResult();
        
		assertNotNull(result);
		assertNotNull(result.getId());

		assertEquals("prova@prova.it", result.getEmail());
		assertEquals("user", result.getUserName());
		assertTrue( result.checkPassword( "password" ) );
		
		assertEquals(1, result.getChirps().size());
		assertEquals(1, result.getFriends().size());
	}


}
