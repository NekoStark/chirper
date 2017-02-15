package it.unifi.ing.chirper.model.persistence.base;

import org.junit.BeforeClass;

public abstract class JpaIT extends JpaTest {

	@BeforeClass
	public static void setUpClass() {
		initEntityManagerFactory( "test_mysql" );
	}

}
