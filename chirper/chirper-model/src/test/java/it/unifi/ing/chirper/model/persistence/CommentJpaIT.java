package it.unifi.ing.chirper.model.persistence;

import org.junit.Test;

import it.unifi.ing.chirper.model.persistence.base.JpaIT;
import it.unifi.ing.chirper.model.persistence.delegates.CommentJpaTestDelegate;

public class CommentJpaIT extends JpaIT{
	
	private CommentJpaTestDelegate commentTest;
	
	@Override
	public void insertData() {
		commentTest = new CommentJpaTestDelegate();
		commentTest.insertData(entityManager);
	}

	@Test
	public void readTest() {
		commentTest.readTest(entityManager);
	}
	

}
