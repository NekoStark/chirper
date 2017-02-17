package it.unifi.ing.chirper.dao.delegates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.reflect.FieldUtils;

import it.unifi.ing.chirper.dao.ChirpDao;
import it.unifi.ing.chirper.dao.UserDao;
import it.unifi.ing.chirper.model.Chirp;
import it.unifi.ing.chirper.model.User;
import it.unifi.ing.chirper.model.factory.ModelFactory;

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
		User friend = userDao.findById(firendId);
		Chirp chirp = chirpDao.findById(chirpId);
		
		assertEquals(1, friend.getFriends().size());
		assertNotNull(chirp);

		userDao.delete(userId);

		assertEquals(0, friend.getFriends().size());
		assertNull(chirpDao.findById(chirpId));
	}


	public void init(EntityManager entityManager) throws IllegalAccessException {
		userDao = new UserDao();
		chirpDao = new ChirpDao();
		
		FieldUtils.writeDeclaredField(userDao, "entityManager", entityManager, true);
		FieldUtils.writeDeclaredField(chirpDao, "entityManager", entityManager, true);
	}

	public void insertData(EntityManager entityManager) {
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
