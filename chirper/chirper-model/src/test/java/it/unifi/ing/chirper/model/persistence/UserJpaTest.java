package it.unifi.ing.chirper.model.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unifi.ing.chirper.model.Chirp;
import it.unifi.ing.chirper.model.User;
import it.unifi.ing.chirper.model.factory.ModelFactory;
import it.unifi.ing.chirper.model.persistence.base.JpaTest;

public class UserJpaTest extends JpaTest {

	private String uuid;
	@BeforeClass
	public static void setUpClass() {
		initEntityManagerFactory( "unit" );
	}
	
	
	@Override
	public void insertData() {
		User user = ModelFactory.user();
		user.setEmail( "prova@prova.it" );
		user.setPassword( "password" );
		user.setUserName( "user" );

		User friend = ModelFactory.user();
		user.addFriend(friend);

		Chirp chirp = ModelFactory.chirp();
		user.addChirp(chirp);

		entityManager.persist(user);

		uuid = user.getUuid();

	}

	@Test
	public void readTest() {
		User result = entityManager
				.createQuery("from User "
						+ "where uuid = :uuid", User.class)
				.setParameter("uuid", uuid)
				.getSingleResult();
        
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(1, result.getChirps().size());
		assertEquals(1, result.getFriends());
		assertEquals("prova@prova.it", result.getEmail());
		assertEquals("user", result.getUserName());
		assertTrue( result.checkPassword( "password" ) );


	}


}
