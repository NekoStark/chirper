package it.unifi.ing.chirper.test.persistence;

public abstract class JpaUnitTest extends JpaTest {

	private static final String PERSISTENCE_NAME = "unit";
	
	@Override
	protected String getPersistenceUnitName() {
		return PERSISTENCE_NAME;
	}
	 
}
