package it.unifi.ing.chirper.services.endpoints;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;

import it.unifi.ing.chirper.dao.UserDao;
import it.unifi.ing.chirper.services.endpoints.delegates.UserTimelineEndpointTestDelegate;
import it.unifi.ing.chirper.test.exception.TestInitializationException;
import it.unifi.ing.chirper.test.services.ServiceUnitTest;

public class UserTimelineEndpointUnitTest extends ServiceUnitTest {

	private UserTimelineEndpointTestDelegate testDelegate;

	@Override
	public void initTest() throws TestInitializationException {
		testDelegate = new UserTimelineEndpointTestDelegate();
		testDelegate.init(entityManager);
		testDelegate.insertData();
	}

	@Test
	public void testGet() {
		testDelegate.testGet();
	}

	@Test
	public void testGetNoFriends() {
		testDelegate.testGetNoFriends();
	}

	@Test
	public void testGetWrongId() {
		testDelegate.testGetWrongId();
	}

	@Override
	protected Set<Object> getEndpoints() throws IllegalAccessException {
		UserDao userDao = new UserDao();
		FieldUtils.writeField(userDao, "entityManager", entityManager, true);

		UserFriendEndpoint userFriendEndpoint = new UserFriendEndpoint();
		FieldUtils.writeField(userFriendEndpoint, "userDao", userDao, true);
		
		UserTimelineEndpoint userTimelineEndpoint = new UserTimelineEndpoint();
		FieldUtils.writeField(userTimelineEndpoint, "userDao", userDao, true);
		
		Set<Object> endpoints = new HashSet<>();
		endpoints.add(userFriendEndpoint);
		endpoints.add(userTimelineEndpoint);
		
		return endpoints;
	}

}
