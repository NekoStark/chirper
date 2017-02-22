package it.unifi.ing.chirper.services.endpoints;

import java.util.Collections;
import java.util.Set;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;

import it.unifi.ing.chirper.dao.ChirpDao;
import it.unifi.ing.chirper.dao.CommentDao;
import it.unifi.ing.chirper.dao.UserDao;
import it.unifi.ing.chirper.services.endpoints.delegates.CommentEndpointTestDelegate;
import it.unifi.ing.chirper.test.services.ServiceUnitTest;

public class CommentEndpointUnitTest extends ServiceUnitTest {
	
	private CommentEndpointTestDelegate commentEndpointTest;
	
	@Override
	public void initTest() throws Exception {
		commentEndpointTest = new CommentEndpointTestDelegate();
		commentEndpointTest.init(entityManager);
		commentEndpointTest.insertData();
	}
	
	@Test
	public void testGet() {
		commentEndpointTest.testGet();
	}
	
	@Test
	public void testSet(){
		commentEndpointTest.testSet();
	}
	
	@Test
	public void testNew() {
		commentEndpointTest.testNew();
	}
	
	@Test
	public void testDel() {
		commentEndpointTest.testDel();
	}
	
	@Override
	protected Set<Object> getEndpoints() throws IllegalAccessException {
		ChirpDao chirpDao = new ChirpDao();
		FieldUtils.writeField(chirpDao, "entityManager", entityManager, true);
		UserDao userDao = new UserDao();
		FieldUtils.writeField(userDao, "entityManager", entityManager, true);
		CommentDao commentDao = new CommentDao();
		FieldUtils.writeField(commentDao, "entityManager", entityManager, true);
		
		CommentEndpoint commentEndpoint = new CommentEndpoint();
		FieldUtils.writeField(commentEndpoint, "chirpDao", chirpDao, true);
		FieldUtils.writeField(commentEndpoint, "userDao", userDao, true);
		FieldUtils.writeField(commentEndpoint, "commentDao", commentDao, true);
		
		return Collections.singleton(commentEndpoint);
	}
	
}
