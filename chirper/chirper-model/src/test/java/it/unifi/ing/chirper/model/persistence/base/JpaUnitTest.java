package it.unifi.ing.chirper.model.persistence.base;


import org.junit.BeforeClass;

public abstract class JpaUnitTest extends JpaTest {

	@BeforeClass
	public static void setUpClass() {
		initEntityManagerFactory( "unit" );
	}
	 
}
