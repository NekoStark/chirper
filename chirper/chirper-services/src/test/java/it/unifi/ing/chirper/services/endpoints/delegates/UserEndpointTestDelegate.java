package it.unifi.ing.chirper.services.endpoints.delegates;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static it.unifi.ing.chirper.model.factory.ModelFactory.user;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import javax.persistence.EntityManager;

import it.unifi.ing.chirper.model.User;

public class UserEndpointTestDelegate {

	private EntityManager entityManager;
	private User u1, u2;

	public void testQuery() {
		//@formatter:off
		get("/users")
			.then()
			.assertThat()
			.body(
				"size()", is(2), 
				"uuid", hasItems(u1.getUuid(), u2.getUuid())
			)
			.statusCode(200);
		//@formatter:on
	}
	
	public void testGet() {
		//@formatter:off
		get("/users/" + u2.getId())
			.then()
			.assertThat()
			.body(
				"uuid", equalTo(u2.getUuid()),
				"userName", equalTo(u2.getUserName()), 
				"password", nullValue(), 
				"email", equalTo(u2.getEmail())
			);

		get("/users/" + u1.getId())
			.then()
			.assertThat()
			.body(
				"uuid", equalTo(u1.getUuid()), 
				"userName", equalTo(u1.getUserName()), 
				"password", nullValue(), 
				"email", equalTo(u1.getEmail())
			);
		//@formatter:on
	}

	public void testGetNotFound() {
		//@formatter:off
		get("/users/999")
			.then()
			.statusCode(404);
		//@formatter:on
	}

	public void testAdd() {
		//@formatter:off
		long userId = given()
				.header("username", "Simone")
				.header("email", "simone@unifi.it")
				.header("password", "pass")
				.when()
				.post("/users")
				.then()
				.extract()
				.jsonPath()
				.getLong("id");

		get("/users/" + userId)
			.then()
			.assertThat()
			.body(
				"uuid", notNullValue(), 
				"userName", equalTo("Simone"),
				"password", nullValue(), 
				"email", equalTo("simone@unifi.it")
			);
		//@formatter:on
	}
	
	public void testAddNoContent() {
		//@formatter:off
		given()
			.when()
			.post("/users")
			.then()
			.statusCode(500);

		given()
			.header("username", "Simone")
			.when()
			.post("/users")
			.then()
			.statusCode(500);

		given()
			.header("username", "Simone")
			.header("email", "simone@unifi.it")
			.when()
			.post("/users")
			.then()
			.statusCode(500);

		given()
			.header("username", "")
			.when()
			.post("/users")
			.then()
			.assertThat()
			.statusCode(500);
		
		given()
			.header("username", "Simone")
			.header("password", "")
			.when()
			.post("/users")
			.then()
			.assertThat()
			.statusCode(500);
		
		given()
			.header("username", "Simone")
			.header("password", "simone@unifi.it")
			.header("password", "")
			.when()
			.post("/users")
			.then()
			.assertThat()
			.statusCode(500);
		//@formatter:on
	}

	public void testUpdate() {
		//@formatter:off
		given()
			.header("username", "Simone")
			.when()
			.put("/users/" + u1.getId())
			.then()
			.assertThat()
			.statusCode(200);

		get("/users/" + u1.getId())
			.then()
			.assertThat()
			.body(
				"uuid", equalTo(u1.getUuid()),
				"userName", equalTo("Simone"),
				"password", nullValue(),
				"email", equalTo("user1@email.it")
			)
			.statusCode(200);
		
		given()
			.header("email", "simone@unifi.it")
			.when()
			.put("/users/" + u1.getId())
			.then()
			.assertThat()
			.statusCode(200);
	
		get("/users/" + u1.getId())
			.then()
			.assertThat()
			.body(
				"uuid", equalTo(u1.getUuid()),
				"userName", equalTo("Simone"),
				"password", nullValue(),
				"email", equalTo("simone@unifi.it")
			)
			.statusCode(200);
		
		given()
			.header("password", "newPassword")
			.when()
			.put("/users/" + u1.getId())
			.then()
			.assertThat()
			.statusCode(200);
	
		get("/users/" + u1.getId())
			.then()
			.assertThat()
			.body(
				"uuid", equalTo(u1.getUuid()),
				"userName", equalTo("Simone"),
				"password", nullValue(),
				"email", equalTo("simone@unifi.it")
			)
			.statusCode(200);
		//@formatter:on
	}
	
	public void testUpdateWrongUser() {
		//@formatter:off
		given()
			.header("username", "Simone")
			.header("email", "simone@unifi.it")
			.when()
			.put("/users/999")
			.then()
			.assertThat()
			.statusCode(404);
		//@formatter:on
	}

	public void testDelete() {
		//@formatter:off
		given()
			.when()
			.delete("/users/" + u1.getId());

		given()
			.when()
			.delete("/users/" + u1.getId())
			.then()
			.statusCode(404);
		//@formatter:on
	}

	//
	// TEST INIT METHODS
	//

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
}
