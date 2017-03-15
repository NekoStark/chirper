package it.unifi.ing.chirper.services.endpoints;

import org.junit.Test;

import it.unifi.ing.chirper.services.endpoints.delegates.ChirpEndpointTestDelegate;
import it.unifi.ing.chirper.test.services.ServiceIT;

public class ChirpEndpointIT extends ServiceIT{
	private ChirpEndpointTestDelegate chirpEndpointIT;

	@Override
	public void initTest() throws Exception {
		chirpEndpointIT = new ChirpEndpointTestDelegate();
		chirpEndpointIT.init(entityManager);
		chirpEndpointIT.insertData();
	}

	@Test
	public void testGet() {
		chirpEndpointIT.testGet();
	}

	@Test
	public void testSet(){
		chirpEndpointIT.testSet();
	}


	@Test
	public void testNew() {
		chirpEndpointIT.testNew();
	}


	@Test
	public void testDel() {
		chirpEndpointIT.testDel();
	}

	
}
