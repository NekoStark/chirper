package it.unifi.ing.chirper.test.services;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import io.restassured.RestAssured;
import it.unifi.ing.chirper.test.exception.TestInitializationException;

public abstract class ServiceIT extends ServiceTest {

	private static final String PERSISTENCE_NAME = "integration";
	
	static {
		Properties properties = new Properties();
		
		try (InputStream in = ServiceIT.class.getResource("test.properties").openStream()) {
			properties.load(in);
			
			RestAssured.baseURI = properties.getProperty("test.baseuri");
			RestAssured.port = Integer.valueOf( properties.getProperty("test.port") );
			RestAssured.basePath = properties.getProperty("test.basepath");
		
		} catch (Exception e) {
			throw new TestInitializationException(e);
		}
	}

	@Override
	protected String getPersistenceUnitName() {
		return PERSISTENCE_NAME;
	}

	@Override
	protected void cleanUpDatabase() {
		List<Object> tables = executeNativeQuery("SELECT TABLE_NAME " + "FROM INFORMATION_SCHEMA.TABLES "
				+ "where table_schema = '" + getDbName() + "'");

		executeNativeUpdate("SET FOREIGN_KEY_CHECKS=0");

		for (Object table : tables) {
			executeNativeUpdate("TRUNCATE TABLE " + table.toString());
		}

		executeNativeUpdate("SET FOREIGN_KEY_CHECKS=1");
	}

	private void executeNativeUpdate(String query) {
		entityManager.createNativeQuery(query).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	private List<Object> executeNativeQuery(String query) {
		return entityManager.createNativeQuery(query).getResultList();
	}

	private String getDbName() {
		String[] connectionUrlToken = entityManagerFactory.getProperties().get("hibernate.connection.url").toString()
				.split("/");

		return connectionUrlToken[connectionUrlToken.length - 1];
	}

}
