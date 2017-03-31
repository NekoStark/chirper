package it.unifi.ing.chirper.services.endpoints;

import org.junit.Test;

import it.unifi.ing.chirper.services.endpoints.delegates.UserEndpointTestDelegate;
import it.unifi.ing.chirper.test.exception.TestInitializationException;
import it.unifi.ing.chirper.test.services.ServiceIT;

public class UserEndpointIT extends ServiceIT {

	private UserEndpointTestDelegate testDelegate;

	@Override
	protected void initTest() throws TestInitializationException {
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

}
