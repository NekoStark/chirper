package it.unifi.ing.chirper.model.persistence.delegates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;

import it.unifi.ing.chirper.model.Chirp;
import it.unifi.ing.chirper.model.Comment;
import it.unifi.ing.chirper.model.User;
import it.unifi.ing.chirper.model.factory.ModelFactory;

public class CommentJpaTestDelegate {
	private String uuid;

	public void insertData(EntityManager entityManager) {
		
		Comment comment = ModelFactory.comment();
		comment.setContent("Content");
		
		User author = ModelFactory.user();
		comment.setAuthor(author);
		
		Chirp chirp = ModelFactory.chirp();
		comment.setChirp(chirp);
		
		entityManager.persist(chirp);
		entityManager.persist(author);
		entityManager.persist(comment);
		
		uuid = comment.getUuid();

	}

	public void readTest(EntityManager entityManager) {

		Comment result = entityManager
				.createQuery("from Comment "
						+ "where uuid = :uuid", Comment.class)
				.setParameter("uuid", uuid)
				.getSingleResult();

		assertNotNull(result);
		assertNotNull(result.getAuthor());
		assertNotNull(result.getChirp());
		
		assertEquals("Content", result.getContent());


	}
}
