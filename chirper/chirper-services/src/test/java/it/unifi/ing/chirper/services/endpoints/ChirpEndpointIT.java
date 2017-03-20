package it.unifi.ing.chirper.services.endpoints;

import org.junit.Test;

import it.unifi.ing.chirper.services.endpoints.delegates.ChirpEndpointTestDelegate;
import it.unifi.ing.chirper.test.services.ServiceIT;

public class ChirpEndpointIT extends ServiceIT {
	private ChirpEndpointTestDelegate testDelegate;

	@Override
	public void initTest() throws Exception {
		testDelegate = new ChirpEndpointTestDelegate();
		testDelegate.init(entityManager);
		testDelegate.insertData();
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
	public void testAdd() {
		testDelegate.testAdd();
	}

	@Test
	public void testAddToBadUser() {
		testDelegate.testAddToBadUser();
	}

	@Test
	public void testAddNoContent() {
		testDelegate.testAddNoContent();
	}

	@Test
	public void testUpdate() {
		testDelegate.testUpdate();
	}

	@Test
	public void testUpdateNoContent() {
		testDelegate.testUpdateNoContent();
	}

	@Test
	public void testDelete() {
		testDelegate.testDelete();
	}

}
