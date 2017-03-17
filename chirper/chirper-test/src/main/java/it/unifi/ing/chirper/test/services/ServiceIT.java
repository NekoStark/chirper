package it.unifi.ing.chirper.test.services;

import java.util.List;

import io.restassured.RestAssured;

public abstract class ServiceIT extends ServiceTest {

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

	@Override
	protected void cleanUpDatabase() {
		List<Object> tables = executeNativeQuery(
					"SELECT TABLE_NAME "
					+ "FROM INFORMATION_SCHEMA.TABLES "
					+ "where table_schema = '"+ getDbName() +"'");
		
		executeNativeUpdate("SET FOREIGN_KEY_CHECKS=0");
		
		for(Object table : tables) {
			System.out.println("TRUNCATE TABLE " + table.toString());
			executeNativeUpdate("TRUNCATE TABLE " + table.toString());
		}
		
		executeNativeUpdate("SET FOREIGN_KEY_CHECKS=1");
	}
	
	private void executeNativeUpdate(String query) {
		entityManager
			.createNativeQuery(query)
			.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	private List<Object> executeNativeQuery(String query) {
		return entityManager
				.createNativeQuery(query)
				.getResultList();
	}
	
	private String getDbName() {
		String[] connectionUrlToken = entityManagerFactory
				.getProperties()
				.get("hibernate.connection.url")
				.toString()
				.split("/");

		String databaseName = connectionUrlToken[ connectionUrlToken.length - 1];
		
		return databaseName;
	}
	
}
