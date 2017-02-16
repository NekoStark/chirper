package it.unifi.ing.chirper.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.unifi.ing.chirper.model.Chirp;
import it.unifi.ing.chirper.model.Comment;
import it.unifi.ing.chirper.model.User;

public class CommentDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	public Comment findById(Long commentId){
		return entityManager.find(Comment.class, commentId);
	}
	
	public List<Comment> findByChirp(Chirp chirp){
		return entityManager
				.createQuery("from Comment c where "
						+ "c.chirp = :chirp", Comment.class)
				.setParameter("chirp", chirp)
				.getResultList();
	}
	
	public List<Comment> findByAuthor(User author){
		return entityManager
				.createQuery("from Comment c where "
						+ "c.author = :auth", Comment.class)
				.setParameter("auth", author)
				.getResultList();
	}
	
	public void save(Comment comment){
		entityManager.persist(comment);
	}
	
	public void delete(Long commentId){
		Comment comment = findById(commentId);
		comment.getChirp().removeComment(comment);
		
		entityManager.remove(comment);
		entityManager.flush();
		
	}

}
