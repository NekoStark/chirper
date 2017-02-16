package it.unifi.ing.chirper.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.unifi.ing.chirper.model.User;

@Stateless
public class UserDao {
	@PersistenceContext
	private EntityManager entityManager;

	public void save(User user) {
		entityManager.persist(user);
	}
	
	public User findById(Long userId){
		return entityManager.find(User.class, userId);
	}
	
	public void delete(Long userId) {
		User user = findById(userId);
		for (User friend : user.getFriends()) {
			friend.removeFriend(user);
		}
		entityManager.remove(user);
		entityManager.flush();
	}
	public List<User> findBUsername(String username) {
		return entityManager
				.createQuery("from User u where "
						+ "u.userName = :uUsr", User.class)
				.setParameter("uUsr", username)
				.getResultList();
	}

}
