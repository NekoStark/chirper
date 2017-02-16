package it.unifi.ing.chirper.dao.delegate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;

import it.unifi.ing.chirper.dao.ChirpDao;
import it.unifi.ing.chirper.dao.UserDao;
import it.unifi.ing.chirper.model.Chirp;
import it.unifi.ing.chirper.model.User;
import it.unifi.ing.chirper.model.factory.ModelFactory;
import it.unifi.ing.chirper.utils.FieldUtils;

public class UserDaoDelegate {

	private UserDao userDao;
	private ChirpDao chirpDao;

	private Long userId;
	private Long firendId;
	private Long chirpId;

	public void testFindById() {
		User result = userDao.findById(userId);

		assertNotNull(result);
	}

	public void testFindByUsername() {
		List<User> result = userDao.findByUsername("username");

		assertEquals(1, result.size());
	}

	public void testSave(){
		User user = ModelFactory.user();
		userDao.save(user);

		assertNotNull(userDao.findById(user.getId()));
	}

	public void testDeleteUser(){
		User firend = userDao.findById(firendId);
		Chirp chirp = chirpDao.findById(chirpId);
		
		assertEquals(1, firend.getFriends().size());
		assertNotNull(chirp);

		userDao.delete(userId);

		assertEquals(0, firend.getFriends().size());
		assertNull(chirpDao.findById(chirpId));
	}

	public void init(EntityManager entityManager) throws Exception {
		userDao = new UserDao();
		chirpDao = new ChirpDao();
		FieldUtils.assignField(userDao, "entityManager", entityManager);
		FieldUtils.assignField(chirpDao, "entityManager", entityManager);
	}

	public void insertData(EntityManager entityManager) throws Exception {
		User user = ModelFactory.user();

		User friend = ModelFactory.user();
		user.addFriend(friend);

		Chirp chirp = ModelFactory.chirp();
		chirp.setAuthor(user);

		user.setEmail("test@unifi.it");
		user.setPassword("password");
		user.setUserName("username");

		entityManager.persist(user);
		entityManager.persist(friend);
		entityManager.persist(chirp);

		entityManager.flush();

		userId = user.getId();
		firendId = friend.getId();
		chirpId = chirp.getId();
	}

}
