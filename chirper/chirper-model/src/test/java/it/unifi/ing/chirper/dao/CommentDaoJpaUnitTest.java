package it.unifi.ing.chirper.dao;

import org.junit.Test;

import it.unifi.ing.chirper.dao.delegates.CommentDaoTestDelegate;
import it.unifi.ing.chirper.test.exception.TestInitializationException;
import it.unifi.ing.chirper.test.persistence.JpaUnitTest;

public class CommentDaoJpaUnitTest extends JpaUnitTest {

	private CommentDaoTestDelegate commentDaoTest;
	
	@Override
	protected void initTest() throws TestInitializationException {
		commentDaoTest = new CommentDaoTestDelegate();
		
		try {
			commentDaoTest.init(entityManager);
		} catch (IllegalAccessException e) {
			throw new TestInitializationException(e);
		}
		
		commentDaoTest.insertData(entityManager);
	}

	@Test
	public void testSave() {
		commentDaoTest.testSave();
	}
	@Test
	public void testFindById() {
		commentDaoTest.testFindById();
	}
	@Test
	public void testFindByChirp() {
		commentDaoTest.testFindByChirp();
	}
	@Test
	public void testFindByAuthor() {
		commentDaoTest.testFindByAuthor();
	}
	@Test
	public void testDelete() {
		commentDaoTest.testDelete();
	}

}
