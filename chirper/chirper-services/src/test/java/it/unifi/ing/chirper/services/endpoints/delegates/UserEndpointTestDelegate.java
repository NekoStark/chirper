package it.unifi.ing.chirper.services.endpoints.delegates;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static it.unifi.ing.chirper.model.factory.ModelFactory.*;
import javax.persistence.EntityManager;

import it.unifi.ing.chirper.model.User;

public class UserEndpointTestDelegate {

	private EntityManager entityManager;
	private User u1, u2;
	
	public void init(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void insertData() {
		u1 = user();
		u1.setUserName("userName 1");
		u1.setPassword("password 1");
		u1.setEmail("user1@email.it");
		u2 = user();
		u2.setUserName("userName 2");
		u2.setPassword("password 2");
		u2.setEmail("user2@email.it");
		entityManager.persist(u1);
		entityManager.persist(u2);
	}
	
	public void testQuery() {
		get("/users")
			.then()
			.assertThat()
			.body(
				"size()", is(2),
				"uuid", hasItems(u1.getUuid(), u2.getUuid())
			);
	}
	
	public void testGet() {
		get("/users/"+u2.getId())
			.then()
			.assertThat()
			.body(
				"uuid", equalTo(u2.getUuid()),
				"userName", equalTo(u2.getUserName()),
				"password", nullValue(),
				"email", equalTo(u2.getEmail())
			);
		
		get("/users/"+u1.getId())
			.then()
			.assertThat()
			.body(
				"uuid", equalTo(u1.getUuid()),
				"userName", equalTo(u1.getUserName()),
				"password", nullValue(),
				"email", equalTo(u1.getEmail())
			);
	}
	
}
