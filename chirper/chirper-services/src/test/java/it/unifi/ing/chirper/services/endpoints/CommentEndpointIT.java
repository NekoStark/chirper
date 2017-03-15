package it.unifi.ing.chirper.services.endpoints;

import org.junit.Test;

import it.unifi.ing.chirper.services.endpoints.delegates.CommentEndpointTestDelegate;
import it.unifi.ing.chirper.test.services.ServiceIT;

public class CommentEndpointIT extends ServiceIT{
	private CommentEndpointTestDelegate commentEndpointIT;

	@Override
	public void initTest() throws Exception {
		commentEndpointIT = new CommentEndpointTestDelegate();
		commentEndpointIT.init(entityManager);
		commentEndpointIT.insertData();
	}

	@Test
	public void testGet() {
		commentEndpointIT.testGet();
	}

	@Test
	public void testSet(){
		commentEndpointIT.testSet();
	}

	@Test
	public void testNew() {
		commentEndpointIT.testNew();
	}

	@Test
	public void testDel() {
		commentEndpointIT.testDel();
	}
}
