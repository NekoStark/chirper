package it.unifi.ing.chirper.dao.delegate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;

import it.unifi.ing.chirper.dao.ChirpDao;
import it.unifi.ing.chirper.dao.UserDao;
import it.unifi.ing.chirper.model.Chirp;
import it.unifi.ing.chirper.model.Comment;
import it.unifi.ing.chirper.model.User;
import it.unifi.ing.chirper.model.factory.ModelFactory;
import it.unifi.ing.chirper.utils.FieldUtils;

public class ChirpDaoDelegate {

	private ChirpDao chirpDao;
	private UserDao userDao;
	
	
	private Long chirpId;
	private Long referenceId;
	private Long authorId;
	
	
	public void testFindById() {
		Chirp result = chirpDao.findById(chirpId);
		assertNotNull(result);
	}
	
	
	public void testSave(){
		Chirp chirp = ModelFactory.chirp();
		chirpDao.save(chirp);
		
		assertNotNull(chirpDao.findById(chirp.getId()));
	}
	
	
	public void testFindByAuthor(){
		List<Chirp> chirps = chirpDao.findByAuthor(userDao.findById(authorId));
		
		assertEquals(1, chirps.size());
	}
	
	
	public void testFindByReference(){
		List<Chirp> chirps = chirpDao.findByReference(chirpDao.findById(referenceId));
		
		assertEquals(1, chirps.size());
	}
	
	public void testDelete(){
		Chirp chirp = chirpDao.findById(chirpId);
		
		
		assertNotNull(chirp);
		assertEquals(1, chirp.getComments().size());
		assertNotNull(chirp.getAuthor());
		

		chirpDao.delete(chirpId);
		
		
		assertNull(chirpDao.findById(chirpId));
		
	}
	
	
	
	public void init(EntityManager entityManager) throws Exception {
		chirpDao = new ChirpDao();
		userDao = new UserDao();
		FieldUtils.assignField(chirpDao, "entityManager", entityManager);
		FieldUtils.assignField(userDao, "entityManager", entityManager);
	}

	public void insertData(EntityManager entityManager) throws Exception {
		Chirp chirp = ModelFactory.chirp();
		chirp.setContent("content");
		
		User author = ModelFactory.user();
		chirp.setAuthor(author);
		
		Chirp reference = ModelFactory.chirp();
		chirp.setReference(reference);
		
		Comment comment = ModelFactory.comment();
		comment.setChirp(chirp);
		
		entityManager.persist(comment);
		entityManager.persist(reference);
		entityManager.persist(author);
		entityManager.persist(chirp);
		
		
		chirpId = chirp.getId();
		authorId = author.getId();
		referenceId = reference.getId();
		
	}
	
}
