package it.unifi.ing.chirper.test.persistence;

public abstract class JpaIT extends JpaTest {

	private static final String PERSISTENCE_NAME = "integration";

	@Override
	protected String getPersistenceUnitName() {
		return PERSISTENCE_NAME;
	}

}
