package it.unifi.ing.chirper.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.unifi.ing.chirper.model.User;

public class UserDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	public void save(User user) {
		entityManager.persist(user);
	}
	
	public User findById(Long userId){
		return entityManager.find(User.class, userId);
	}
	
	public void delete(User user) {
		for (User friend : user.getFriends()) {
			friend.removeFriend(user);
		}
		entityManager.remove(user);
	}
	
	public List<User> findByUsername(String username) {
		return entityManager
				.createQuery("from User u where "
						+ "u.userName = :uUsr", User.class)
				.setParameter("uUsr", username)
				.getResultList();
	}
	
	public List<User> allUser() {
		return entityManager
				.createQuery("from User ", User.class)
				.getResultList();
	}

}
