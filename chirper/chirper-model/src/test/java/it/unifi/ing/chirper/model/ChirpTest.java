package it.unifi.ing.chirper.model;

import static it.unifi.ing.chirper.model.factory.ModelFactory.chirp;
import static it.unifi.ing.chirper.model.factory.ModelFactory.comment;
import static it.unifi.ing.chirper.model.factory.ModelFactory.user;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ChirpTest {

	private Chirp chirp;
	
	@Before
	public void setUp() {
		chirp = chirp();
	}

	@Test
	public void testBasicProperties() {
		String content = "an example of a chirp content";
		chirp.setContent(content);
		
		assertEquals(content, chirp.getContent());
	}
	
	// testo i metodi relativi all'autore di un chirp, in particolare
	// che l'operazione di aggiunta sia bidirezionale
	@Test
	public void testAuthorMethods() {
		User user = user();
		chirp.setAuthor(user);
		
		assertEquals(user, chirp.getAuthor());
		assertEquals(1, user.getChirps().size());
		assertEquals(chirp, user.getChirps().get(0));
	}
	
	// testo i metodi relativi all'operazione di condivisione di un chirp
	@Test
	public void testReference() {
		Chirp originalChirp = chirp();
		chirp.setReference(originalChirp);
		
		assertEquals(originalChirp, chirp.getReference());
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testAddCommentException() {
		chirp.getComments().add( comment() );
	}
	
}
