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

public class ChirpJpaTestDelegate {

	private EntityManager entityManager;
	
	private String uuid;
	
	public void init(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void insertData() {
		Chirp chirp = chirp();
		Chirp reference = chirp();
		chirp.setReference(reference);
		chirp.setContent("Content");
		
		User author = user();
		chirp.setAuthor(author);
	
		Comment comment = comment();
		comment.setChirp(chirp);
		
		entityManager.persist(chirp);
		entityManager.persist(reference);
		entityManager.persist(comment);
		entityManager.persist(author);
		
		uuid = chirp.getUuid();

	}

	public void readTest() {
		
		Chirp result = entityManager
				.createQuery("from Chirp "
						+ "where uuid = :uuid", Chirp.class)
				.setParameter("uuid", uuid)
				.getSingleResult();
		
		assertNotNull(result);
		assertNotNull(result.getAuthor());
		assertNotNull(result.getReference());
		
		assertEquals("Content", result.getContent());
		assertEquals(1, result.getComments().size());
		
	}
	
}
