package it.unifi.ing.chirper.model;

import static it.unifi.ing.chirper.model.factory.ModelFactory.user;
import static it.unifi.ing.chirper.model.factory.ModelFactory.chirp;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

	private User user;
	
	@Before
	public void setUp() {
		user = user();
	}
	
	@Test
	public void testBasicProperties() {
		String userName = "PippoFranco";
		String email = "pippo.franco@bagaglino.it";
		user.setUserName(userName);
		user.setEmail(email);
		
		assertEquals(userName, user.getUserName());
		assertEquals(email, user.getEmail());
	}
	
	// testo i metodi relativi alla password, in particolare che una volta 
	// settata la password, poi il controllo su checkPassword sia corretto
	@Test
	public void testPasswordMethods() {
		String plainPassword = "LeoGullotta";
		user.setPassword(plainPassword);
		
		assertTrue(user.checkPassword(plainPassword));
		assertFalse(user.checkPassword("otherPassword"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPasswordMethodsException() {
		user.setPassword(null);
	}

	// testo i metodi relativi alla lista di amici, in particolare che l'aggiunta
	// sia un operazione bidirezionale.
	@Test
	public void testFriendsMethods() {
		User friend = user();
		user.addFriend(friend);
		
		assertEquals(1, user.getFriends().size());
		assertEquals(friend, user.getFriends().iterator().next());
		assertEquals(1, friend.getFriends().size());
		assertEquals(user, friend.getFriends().iterator().next());
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testAddFriendException() {
		user.getFriends().add( user() );
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testAddChirpException() {
		user.getChirps().add( chirp() );
	}
	
}
