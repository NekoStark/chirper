package it.unifi.ing.chirper.services.endpoints;

import org.junit.Test;

import it.unifi.ing.chirper.services.endpoints.delegates.UserFriendEndpointTestDelegate;
import it.unifi.ing.chirper.test.exception.TestInitializationException;
import it.unifi.ing.chirper.test.services.ServiceIT;

public class UserFriendEndpointIT extends ServiceIT {

	private UserFriendEndpointTestDelegate testDelegate;

	@Override
	public void initTest() throws TestInitializationException {
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
}
