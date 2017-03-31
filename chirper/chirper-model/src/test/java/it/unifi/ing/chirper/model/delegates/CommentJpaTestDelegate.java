package it.unifi.ing.chirper.model.delegates;

import static it.unifi.ing.chirper.model.factory.ModelFactory.chirp;
import static it.unifi.ing.chirper.model.factory.ModelFactory.comment;
import static it.unifi.ing.chirper.model.factory.ModelFactory.user;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;

import it.unifi.ing.chirper.model.Chirp;
import it.unifi.ing.chirper.model.Comment;
import it.unifi.ing.chirper.model.User;

public class CommentJpaTestDelegate {
	
	private EntityManager entityManager;

	private String uuid;
	
	public void init(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void insertData() {
		Comment comment = comment();
		comment.setContent("Content");
		
		User author = user();
		comment.setAuthor(author);
		
		Chirp chirp = chirp();
		comment.setChirp(chirp);
		
		entityManager.persist(chirp);
		entityManager.persist(author);
		entityManager.persist(comment);
		
		uuid = comment.getUuid();

	}

	public void readTest() {
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
