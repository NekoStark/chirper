package it.unifi.ing.chirper.model.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import it.unifi.ing.chirper.model.Chirp;
import it.unifi.ing.chirper.model.User;
import it.unifi.ing.chirper.model.factory.ModelFactory;
import it.unifi.ing.chirper.model.persistence.base.JpaTest;

public class ChirpJpaTest extends JpaTest{

	private String uuid;
	
	@Override
	protected void insertData() throws Exception {
		Chirp chirp = ModelFactory.chirp();
		Chirp reference = ModelFactory.chirp();
		User author = ModelFactory.user();
		
		chirp.setContent("chirp_content");
		chirp.setReference(reference);
		chirp.setAuthor(author);
		
		uuid = chirp.getUuid();
	}
	
	
	@Test
	public void readTest() {
		
		Chirp result = entityManager
				.createQuery("from User "
						+ "where uuid = :uuid", Chirp.class)
				.setParameter("uuid", uuid)
				.getSingleResult();

		
		assertNotNull(result);
		assertNotNull(result.getAuthor());
		assertNotNull(result.getReference());
//		assertEquals(1, result.getComments().size());
//		assertEquals("chirp_content", result.getComments());
	}
}
