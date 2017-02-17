package it.unifi.ing.chirper.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.unifi.ing.chirper.model.Chirp;
import it.unifi.ing.chirper.model.User;

public class ChirpDao {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(Chirp chirp) {
		entityManager.persist(chirp);
	}
	
	public Chirp findById(Long chirpId){
		return entityManager.find(Chirp.class, chirpId);
	}
	
	public void delete(Long chirpId) {
		Chirp chirp = findById(chirpId);
		chirp.getAuthor().removeChirp(chirp);
		entityManager.remove(chirp);
		entityManager.flush();
	}

	public List<Chirp> findByAuthor(User author){
		return entityManager
				.createQuery("from Chirp c where "
						+ "c.author = :auth", Chirp.class)
				.setParameter("auth", author)
				.getResultList();
	}
	
	public List<Chirp> findByReference(Chirp reference){
		return entityManager
				.createQuery("from Chirp c where "
						+ "c.reference = :ref", Chirp.class)
				.setParameter("ref", reference)
				.getResultList();
	}

}

