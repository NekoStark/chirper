package it.unifi.ing.chirper.services.endpoints;

import org.junit.Test;

import it.unifi.ing.chirper.services.endpoints.delegates.UserTimelineEndpointTestDelegate;
import it.unifi.ing.chirper.test.services.ServiceIT;

public class UserTimelineEndpointIT extends ServiceIT {

	private UserTimelineEndpointTestDelegate testDelegate;

	@Override
	public void initTest() throws Exception {
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
	
}
