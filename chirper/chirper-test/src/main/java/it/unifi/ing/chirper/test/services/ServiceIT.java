package it.unifi.ing.chirper.test.services;

import io.restassured.RestAssured;

public abstract class ServiceIT extends ServiceTest{

	private static final String PERSISTENCE_NAME = "integration";
	
	@Override
	protected String getPersistenceUnitName() {
		return PERSISTENCE_NAME;
	}

	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/chirper-services/rest/1.0";
		
	}



}
