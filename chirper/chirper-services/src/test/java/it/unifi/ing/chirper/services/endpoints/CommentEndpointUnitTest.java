package it.unifi.ing.chirper.services.endpoints;

import java.util.Collections;
import java.util.Set;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;

import it.unifi.ing.chirper.dao.ChirpDao;
import it.unifi.ing.chirper.dao.CommentDao;
import it.unifi.ing.chirper.dao.UserDao;
import it.unifi.ing.chirper.services.endpoints.delegates.CommentEndpointTestDelegate;
import it.unifi.ing.chirper.test.exception.TestInitializationException;
import it.unifi.ing.chirper.test.services.ServiceUnitTest;

public class CommentEndpointUnitTest extends ServiceUnitTest {

	private CommentEndpointTestDelegate testDelegate;

	@Override
	public void initTest() throws TestInitializationException {
		testDelegate = new CommentEndpointTestDelegate();
		testDelegate.init(entityManager);
		testDelegate.insertData();
	}

	@Test
	public void testQuery() {
		testDelegate.testQuery();
	}

	@Test
	public void testQueryWrongId() {
		testDelegate.testQueryWrongId();
	}

	@Test
	public void testAdd() {
		testDelegate.testAdd();
	}

	@Test
	public void testAddNoContent() {
		testDelegate.testAddNoContent();
	}
	
	@Test
	public void testAddWrongIds() {
		testDelegate.testAddWrongIds();
	}

	@Test
	public void testUpdate() {
		testDelegate.testUpdate();
	}

	@Test
	public void testUpdateWrongIds() {
		testDelegate.testUpdateWrongIds();
	}

	@Test
	public void testDelete() {
		testDelegate.testDelete();
	}

	@Override
	protected Set<Object> getEndpoints() throws IllegalAccessException {
		ChirpDao chirpDao = new ChirpDao();
		FieldUtils.writeField(chirpDao, "entityManager", entityManager, true);
		UserDao userDao = new UserDao();
		FieldUtils.writeField(userDao, "entityManager", entityManager, true);
		CommentDao commentDao = new CommentDao();
		FieldUtils.writeField(commentDao, "entityManager", entityManager, true);

		CommentEndpoint commentEndpoint = new CommentEndpoint();
		FieldUtils.writeField(commentEndpoint, "chirpDao", chirpDao, true);
		FieldUtils.writeField(commentEndpoint, "userDao", userDao, true);
		FieldUtils.writeField(commentEndpoint, "commentDao", commentDao, true);

		return Collections.singleton(commentEndpoint);
	}

}
