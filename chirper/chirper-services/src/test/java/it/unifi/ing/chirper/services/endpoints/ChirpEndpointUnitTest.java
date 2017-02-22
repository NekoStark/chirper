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

	private ChirpEndpointTestDelegate chirpEndpointTest;
	
	@Override
	public void initTest() throws Exception {
		chirpEndpointTest = new ChirpEndpointTestDelegate();
		chirpEndpointTest.init(entityManager);
		chirpEndpointTest.insertData();
	}
	
	@Test
	public void testGet() {
		chirpEndpointTest.testGet();
	}
	
	@Test
	public void testSet(){
		chirpEndpointTest.testSet();
	}
	
	
	@Test
	public void testNew() {
		chirpEndpointTest.testNew();
	}
	
	
	@Test
	public void testDel() {
		chirpEndpointTest.testDel();
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
