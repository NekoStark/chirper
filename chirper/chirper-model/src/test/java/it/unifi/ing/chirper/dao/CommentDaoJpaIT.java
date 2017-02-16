package it.unifi.ing.chirper.dao;

import org.junit.Test;

import it.unifi.ing.chirper.dao.delegate.CommentDaoDelegate;
import it.unifi.ing.chirper.model.persistence.base.JpaIT;

public class CommentDaoJpaIT extends JpaIT {
	private CommentDaoDelegate commentDaoTest;

	@Override
	protected void initTest() throws Exception {
		commentDaoTest = new CommentDaoDelegate();

		commentDaoTest.init(entityManager);
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
