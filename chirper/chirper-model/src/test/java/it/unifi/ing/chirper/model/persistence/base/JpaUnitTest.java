package it.unifi.ing.chirper.model.persistence.base;

public abstract class JpaUnitTest extends JpaTest {

	private static final String PERSISTENCE_NAME = "unit";
	
	@Override
	protected String getPersistenceUnitName() {
		return PERSISTENCE_NAME;
	}
	 
}
