package it.unifi.ing.chirper.services.endpoints;

import it.unifi.ing.chirper.services.endpoints.delegates.UserEndpointTestDelegate;
import it.unifi.ing.chirper.test.services.ServiceIT;

public class UserEndPointIT extends ServiceIT{

	private UserEndpointTestDelegate userEndpointIT;
	


	@Override
	protected void initTest() throws Exception {
		userEndpointIT = new UserEndpointTestDelegate();
		userEndpointIT.init(entityManager);
		userEndpointIT.insertData();
	}

}
