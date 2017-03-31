package it.unifi.ing.chirper.test.services;

import java.util.Set;

import org.jboss.resteasy.plugins.server.tjws.TJWSEmbeddedJaxrsServer;

import io.restassured.RestAssured;
import it.unifi.ing.chirper.test.exception.TestInitializationException;

public abstract class ServiceUnitTest extends ServiceTest {

	private static final String PERSISTENCE_NAME = "unit";
	private static final Integer PORT = 12345;

	protected TJWSEmbeddedJaxrsServer server;

	static {
		RestAssured.port = PORT;
	}
	
	@Override
	protected String getPersistenceUnitName() {
		return PERSISTENCE_NAME;
	}

	@Override
	public void setUp() throws TestInitializationException {
		super.setUp();

		server = new TJWSEmbeddedJaxrsServer();
		server.setPort(PORT);
		try {
			for (Object endpoint : getEndpoints()) {
				server.getDeployment().getResources().add(endpoint);
			}
		} catch (IllegalAccessException e) {
			throw new TestInitializationException(e);
		}
		server.start();
	}

	@Override
	public void tearDown() {
		super.tearDown();

		server.stop();
	}

	protected abstract Set<Object> getEndpoints() throws IllegalAccessException;

}
