package it.unifi.ing.chirper.dao;

import org.junit.Test;

import it.unifi.ing.chirper.dao.delegates.CommentDaoTestDelegate;
import it.unifi.ing.chirper.test.persistence.JpaIT;
import it.unifi.ing.chirper.test.persistence.JpaTestInitializationException;

public class CommentDaoJpaIT extends JpaIT {
	private CommentDaoTestDelegate commentDaoTest;

	@Override
	protected void initTest() throws JpaTestInitializationException {
		commentDaoTest = new CommentDaoTestDelegate();

		try {
			commentDaoTest.init(entityManager);
		} catch (IllegalAccessException e) {
			throw new JpaTestInitializationException(e);
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
