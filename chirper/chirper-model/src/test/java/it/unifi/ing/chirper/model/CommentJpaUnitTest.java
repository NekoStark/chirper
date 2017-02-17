package it.unifi.ing.chirper.model;

import org.junit.Test;

import it.unifi.ing.chirper.model.delegates.CommentJpaTestDelegate;
import it.unifi.ing.chirper.test.persistence.JpaUnitTest;

public class CommentJpaUnitTest extends JpaUnitTest{
	
	private CommentJpaTestDelegate commentTest;
	
	@Override
	public void initTest() {
		commentTest = new CommentJpaTestDelegate();
		commentTest.init(entityManager);
		commentTest.insertData();
	}

	@Test
	public void readTest() {
		commentTest.readTest();
	}
	

}
