package it.unifi.ing.chirper.services.endpoints;

import java.util.Collections;
import java.util.Set;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;

import it.unifi.ing.chirper.dao.UserDao;
import it.unifi.ing.chirper.services.endpoints.delegates.UserEndpointTestDelegate;
import it.unifi.ing.chirper.test.exception.TestInitializationException;
import it.unifi.ing.chirper.test.services.ServiceUnitTest;

public class UserEndpointUnitTest extends ServiceUnitTest {

	private UserEndpointTestDelegate testDelegate;

	@Override
	public void initTest() throws TestInitializationException {
		testDelegate = new UserEndpointTestDelegate();
		testDelegate.init(entityManager);
		testDelegate.insertData();
	}

	@Test
	public void testQuery() {
		testDelegate.testQuery();
	}

	@Test
	public void testGet() {
		testDelegate.testGet();
	}

	@Test
	public void testGetNotFound() {
		testDelegate.testGetNotFound();
	}

	@Test
	public void testUpdate() {
		testDelegate.testUpdate();
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
	public void testUpdateWrongUser() {
		testDelegate.testUpdateWrongUser();
	}

	@Test
	public void testDelete() {
		testDelegate.testDelete();
	}

	@Override
	protected Set<Object> getEndpoints() throws IllegalAccessException {
		UserDao userDao = new UserDao();
		FieldUtils.writeField(userDao, "entityManager", entityManager, true);

		UserEndpoint userEndpoint = new UserEndpoint();
		FieldUtils.writeField(userEndpoint, "userDao", userDao, true);

		return Collections.singleton(userEndpoint);
	}

}
