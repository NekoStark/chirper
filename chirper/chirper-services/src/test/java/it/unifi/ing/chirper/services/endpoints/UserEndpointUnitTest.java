package it.unifi.ing.chirper.services.endpoints;

import java.util.Collections;
import java.util.Set;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;

import it.unifi.ing.chirper.dao.UserDao;
import it.unifi.ing.chirper.services.endpoints.delegates.UserEndpointTestDelegate;
import it.unifi.ing.chirper.test.services.ServiceUnitTest;

public class UserEndpointUnitTest extends ServiceUnitTest {

	private UserEndpointTestDelegate userEndpointTest;
	
	@Override
	public void initTest() throws Exception {
		userEndpointTest = new UserEndpointTestDelegate();
		userEndpointTest.init(entityManager);
		userEndpointTest.insertData();
	}
	
	@Test
	public void testQuery() {
		userEndpointTest.testQuery();
	}
	
	@Test
	public void testGet() {
		userEndpointTest.testGet();
	}
	
	@Test
	public void testSet(){
		userEndpointTest.testSet();
	}
	
	
	@Test
	public void testNew() {
		userEndpointTest.testNew();
	}
	
	
	@Test
	public void testDel() {
		userEndpointTest.testDel();
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
