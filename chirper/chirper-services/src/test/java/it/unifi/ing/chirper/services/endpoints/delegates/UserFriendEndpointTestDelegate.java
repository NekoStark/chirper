package it.unifi.ing.chirper.services.endpoints.delegates;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.post;
import static it.unifi.ing.chirper.model.factory.ModelFactory.user;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

import javax.persistence.EntityManager;

import it.unifi.ing.chirper.model.User;

public class UserFriendEndpointTestDelegate {

	private EntityManager entityManager;
	private User u1, u2, u3;
	
	public void testQuery() {
		//@formatter:off
		get("/users/"+u1.getId()+"/friends")
			.then()
			.assertThat()
			.body(
				"size()", is(1), 
				"uuid", hasItems(u2.getUuid())
			)
			.statusCode(200);
		
		get("/users/"+u2.getId()+"/friends")
			.then()
			.assertThat()
			.body(
				"size()", is(1), 
				"uuid", hasItems(u1.getUuid())
			)
			.statusCode(200);
		//@formatter:on
	}
	
	public void testQueryWrongId() {
		//@formatter:off
		get("/users/9999/friends")
			.then()
			.statusCode(404);
		//@formatter:on
	}
	
	public void testAdd() {
		//@formatter:off
		post("/users/"+u1.getId()+"/friends/add/"+u3.getId())
			.then()
			.statusCode(200);
		
		get("/users/"+u1.getId()+"/friends")
			.then()
			.assertThat()
			.body(
				"size()", is(2), 
				"uuid", hasItems(u2.getUuid(), u3.getUuid())
			);
		
		get("/users/"+u3.getId()+"/friends")
		.then()
		.assertThat()
		.body(
			"size()", is(1), 
			"uuid", hasItems(u1.getUuid())
		);
		//@formatter:on
	}
	
	public void testAddWrongIds() {
		//@formatter:off
		post("/users/"+u1.getId()+"/friends/add/9999")
			.then()
			.statusCode(404);
		
		post("/users/9999/friends/add/"+u1.getId())
			.then()
			.statusCode(404);
		//@formatter:on
	}
	
	public void testRemove() {
		//@formatter:off
		post("/users/"+u1.getId()+"/friends/remove/"+u2.getId())
			.then()
			.statusCode(200);
		
		get("/users/"+u1.getId()+"/friends")
			.then()
			.assertThat()
			.body("size()", is(0));
		
		get("/users/"+u2.getId()+"/friends")
		.then()
		.assertThat()
		.body("size()", is(0));
		//@formatter:on
	}
	
	public void testRemoveWrongIds() {
		//@formatter:off
		post("/users/9999/friends/remove/"+u2.getId())
			.then()
			.statusCode(404);
			
		post("/users/"+u1.getId()+"/friends/remove/9999")
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
		u2 = user();
		u3 = user();
		
		u1.addFriend(u2);
		
		entityManager.persist(u1);
		entityManager.persist(u2);
		entityManager.persist(u3);
	}
}
