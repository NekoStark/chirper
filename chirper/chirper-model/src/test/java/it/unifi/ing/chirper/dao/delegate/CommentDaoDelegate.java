package it.unifi.ing.chirper.dao.delegate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;

import it.unifi.ing.chirper.dao.ChirpDao;
import it.unifi.ing.chirper.dao.CommentDao;
import it.unifi.ing.chirper.dao.UserDao;
import it.unifi.ing.chirper.model.Chirp;
import it.unifi.ing.chirper.model.Comment;
import it.unifi.ing.chirper.model.User;
import it.unifi.ing.chirper.model.factory.ModelFactory;
import it.unifi.ing.chirper.utils.FieldUtils;

public class CommentDaoDelegate {
	private CommentDao commentDao;
	private ChirpDao chirpDao;
	private UserDao userDao;
	
	private Long commentId;
	private Long chirpId;
	private Long authorId;
	
	
	public void testSave(){
		Comment comment = ModelFactory.comment();
		commentDao.save(comment);
		
		assertNotNull(commentDao.findById(comment.getId()));
	}
	
	public void testFindById(){
		assertNotNull(commentDao.findById(commentId));
	}
	
	public void testFindByChirp(){
		Chirp chirp = chirpDao.findById(chirpId);
		
		assertNotNull(chirp);
		assertEquals(1, commentDao.findByChirp(chirp).size());
	}
	
	public void testFindByAuthor(){
		User author = userDao.findById(authorId);
		
		assertNotNull(author);
		assertEquals(1, commentDao.findByAuthor(author).size());
	}
	
	public void testDelete(){
		Comment comment = commentDao.findById(commentId);
		
		assertNotNull(comment);
		assertNotNull(comment.getAuthor());
		assertNotNull(comment.getChirp());
		
		commentDao.delete(commentId);
		
		assertNull(commentDao.findById(comment.getId()));
	}
	
	public void init(EntityManager entityManager) throws Exception {
		commentDao = new CommentDao();
		chirpDao = new ChirpDao();
		userDao = new UserDao();
		
		FieldUtils.assignField(userDao, "entityManager", entityManager);
		FieldUtils.assignField(chirpDao, "entityManager", entityManager);
		FieldUtils.assignField(commentDao, "entityManager", entityManager);
	}

	public void insertData(EntityManager entityManager) throws Exception {
		Comment comment = ModelFactory.comment();
		comment.setContent("Content");
		
		User author = ModelFactory.user();
		comment.setAuthor(author);
		
		Chirp chirp = ModelFactory.chirp();
		comment.setChirp(chirp);
		
		entityManager.persist(chirp);
		entityManager.persist(author);
		entityManager.persist(comment);
		
		commentId = comment.getId();
		chirpId = chirp.getId();
		authorId = author.getId();
	}
}
