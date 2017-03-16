package it.unifi.ing.chirper.services.endpoints;

import java.util.Collections;
import java.util.Set;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;

import it.unifi.ing.chirper.dao.ChirpDao;
import it.unifi.ing.chirper.dao.UserDao;
import it.unifi.ing.chirper.services.endpoints.delegates.ChirpEndpointTestDelegate;
import it.unifi.ing.chirper.test.services.ServiceUnitTest;

public class ChirpEndpointUnitTest extends ServiceUnitTest {

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
	
	@Override
	protected Set<Object> getEndpoints() throws IllegalAccessException {
		ChirpDao chirpDao = new ChirpDao();
		FieldUtils.writeField(chirpDao, "entityManager", entityManager, true);
		UserDao userDao = new UserDao();
		FieldUtils.writeField(userDao, "entityManager", entityManager, true);

		ChirpEndpoint chirpEndpoint = new ChirpEndpoint();
		FieldUtils.writeField(chirpEndpoint, "chirpDao", chirpDao, true);
		FieldUtils.writeField(chirpEndpoint, "userDao", userDao, true);

		return Collections.singleton(chirpEndpoint);
	}

}
