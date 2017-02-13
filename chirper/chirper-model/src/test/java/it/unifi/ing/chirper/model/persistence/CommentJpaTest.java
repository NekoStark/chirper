package it.unifi.ing.chirper.model.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unifi.ing.chirper.model.Chirp;
import it.unifi.ing.chirper.model.Comment;
import it.unifi.ing.chirper.model.User;
import it.unifi.ing.chirper.model.factory.ModelFactory;
import it.unifi.ing.chirper.model.persistence.base.JpaTest;

public class CommentJpaTest extends JpaTest{

	
	private String uuid;
	
	@BeforeClass
	public static void setUpClass() {
		initEntityManagerFactory( "unit" );
	}
	
	@Override
	protected void insertData() throws Exception {
		Comment comment = ModelFactory.comment();
		comment.setContent("Comment_Content");
		
		User author = ModelFactory.user();
		comment.setAuthor(author);
		
		Chirp chirp = ModelFactory.chirp();
		comment.setChirp(chirp);
		
		
		uuid = comment.getUuid();
		
	}

	@Test
	public void readTest() {
		Comment result = entityManager
				.createQuery("from User "
						+ "where uuid = :uuid", Comment.class)
				.setParameter("uuid", uuid)
				.getSingleResult();
		
		assertNotNull(result);
		assertNotNull(result.getAuthor());
		assertNotNull(result.getChirp());
		assertEquals("Comment_Content", result.getContent());

	}
}
