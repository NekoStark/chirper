package it.unifi.ing.chirper.services.endpoints;

import java.util.Collections;
import java.util.Set;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;

import it.unifi.ing.chirper.dao.UserDao;
import it.unifi.ing.chirper.services.endpoints.delegates.UserFriendEndpointTestDelegate;
import it.unifi.ing.chirper.test.services.ServiceUnitTest;

public class UserFriendEndpointUnitTest extends ServiceUnitTest {

	private UserFriendEndpointTestDelegate testDelegate;

	@Override
	public void initTest() throws Exception {
		testDelegate = new UserFriendEndpointTestDelegate();
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
	public void testAddWrongIds() {
		testDelegate.testAddWrongIds();
	}
	
	@Test
	public void testRemove() {
		testDelegate.testRemove();
	}
	
	@Test
	public void testRemoveWrongIds() {
		testDelegate.testRemoveWrongIds();
	}

	@Override
	protected Set<Object> getEndpoints() throws IllegalAccessException {
		UserDao userDao = new UserDao();
		FieldUtils.writeField(userDao, "entityManager", entityManager, true);

		UserFriendEndpoint userFriendEndpoint = new UserFriendEndpoint();
		FieldUtils.writeField(userFriendEndpoint, "userDao", userDao, true);

		return Collections.singleton(userFriendEndpoint);
	}

}
