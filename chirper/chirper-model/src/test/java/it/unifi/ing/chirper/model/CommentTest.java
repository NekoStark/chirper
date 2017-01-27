package it.unifi.ing.chirper.model;

import static it.unifi.ing.chirper.model.factory.ModelFactory.chirp;
import static it.unifi.ing.chirper.model.factory.ModelFactory.user;
import static it.unifi.ing.chirper.model.factory.ModelFactory.comment;
import static org.junit.Assert.assertEquals;

import it.unifi.ing.chirper.model.Comment;
import it.unifi.ing.chirper.model.Chirp;
import it.unifi.ing.chirper.model.User;
import org.junit.Before;
import org.junit.Test;

public class CommentTest {

	private Comment comment;
	
	@Before
	public void setUp() {
		comment = comment();
	}

	@Test
	public void testBasicProperties() {
		String content = "an example of a comment content";
		comment.setContent(content);
		User user = user();
		comment.setAuthor(user);
		
		assertEquals(content, comment.getContent());
		assertEquals(user, comment.getAuthor());
	}
	
	// testo i metodi relativi al chirp che sto commentando, in particolare
	// che l'operazione di aggiunta sia bidirezionale
	@Test
	public void testChirpMethods() {
		Chirp chirp = chirp();
		comment.setChirp(chirp);
		
		assertEquals(chirp, comment.getChirp());
		assertEquals(1, chirp.getComments().size());
		assertEquals(comment, chirp.getComments().get(0));
	}
	
}
