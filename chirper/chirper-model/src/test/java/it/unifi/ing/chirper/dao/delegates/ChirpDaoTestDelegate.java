package it.unifi.ing.chirper.dao.delegates;

import static it.unifi.ing.chirper.model.factory.ModelFactory.chirp;
import static it.unifi.ing.chirper.model.factory.ModelFactory.comment;
import static it.unifi.ing.chirper.model.factory.ModelFactory.user;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.reflect.FieldUtils;

import it.unifi.ing.chirper.dao.ChirpDao;
import it.unifi.ing.chirper.dao.UserDao;
import it.unifi.ing.chirper.model.Chirp;
import it.unifi.ing.chirper.model.Comment;
import it.unifi.ing.chirper.model.User;

public class ChirpDaoTestDelegate {

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
		Chirp chirp = chirp();
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
		
		chirpDao.delete(chirpDao.findById(chirpId));
		
		assertNull(chirpDao.findById(chirpId));
		
	}
	
	public void init(EntityManager entityManager) throws IllegalAccessException {
		chirpDao = new ChirpDao();
		userDao = new UserDao();
		
		FieldUtils.writeField(chirpDao, "entityManager", entityManager, true);
		FieldUtils.writeField(userDao, "entityManager", entityManager, true);
	}

	public void insertData(EntityManager entityManager) {
		Chirp chirp = chirp();
		chirp.setContent("content");
		
		User author = user();
		chirp.setAuthor(author);
		
		Chirp reference = chirp();
		chirp.setReference(reference);
		
		Comment comment = comment();
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
