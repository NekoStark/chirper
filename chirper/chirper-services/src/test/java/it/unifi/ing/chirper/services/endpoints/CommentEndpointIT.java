package it.unifi.ing.chirper.services.endpoints;

import org.junit.Test;

import it.unifi.ing.chirper.services.endpoints.delegates.CommentEndpointTestDelegate;
import it.unifi.ing.chirper.test.exception.TestInitializationException;
import it.unifi.ing.chirper.test.services.ServiceIT;

public class CommentEndpointIT extends ServiceIT {
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
}
