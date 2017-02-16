package it.unifi.ing.chirper.model.persistence.delegates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;

import it.unifi.ing.chirper.model.Chirp;
import it.unifi.ing.chirper.model.Comment;
import it.unifi.ing.chirper.model.User;
import it.unifi.ing.chirper.model.factory.ModelFactory;

public class ChirpJpaTestDelegate {

	private String uuid;
	
	public void insertData(EntityManager entityManager) {
		Chirp chirp = ModelFactory.chirp();
		Chirp reference = ModelFactory.chirp();
		chirp.setReference(reference);
		chirp.setContent("Content");
		
		User author = ModelFactory.user();
		chirp.setAuthor(author);
	
		Comment comment = ModelFactory.comment();
		comment.setChirp(chirp);
		
		entityManager.persist(chirp);
		entityManager.persist(reference);
		entityManager.persist(comment);
		entityManager.persist(author);
		
		uuid = chirp.getUuid();
		
		
		
		
	}

	public void readTest(EntityManager entityManager) {
		
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
