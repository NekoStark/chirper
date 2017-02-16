package it.unifi.ing.chirper.model.persistence;

import org.junit.Test;

import it.unifi.ing.chirper.model.persistence.base.JpaUnitTest;
import it.unifi.ing.chirper.model.persistence.delegates.CommentJpaTestDelegate;

public class CommentJpaUnitTest extends JpaUnitTest{
	
	private CommentJpaTestDelegate commentTest;
	
	@Override
	public void initTest() {
		commentTest = new CommentJpaTestDelegate();
		commentTest.insertData(entityManager);
	}

	@Test
	public void readTest() {
		commentTest.readTest(entityManager);
	}
	

}
