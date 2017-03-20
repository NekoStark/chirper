package it.unifi.ing.chirper.services.endpoints.delegates;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.post;
import static it.unifi.ing.chirper.model.factory.ModelFactory.chirp;
import static it.unifi.ing.chirper.model.factory.ModelFactory.user;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

import javax.persistence.EntityManager;

import it.unifi.ing.chirper.model.Chirp;
import it.unifi.ing.chirper.model.User;

public class UserTimelineEndpointTestDelegate {

	private EntityManager entityManager;
	private User u1, u2;
	private Chirp c1, c2, c3;

	public void testGet() {
		//@formatter:off
		get("/users/"+u1.getId()+"/timeline")
			.then()
			.assertThat()
			.body(
				"size()", is(2), 
				"uuid", hasItems(c2.getUuid(), c3.getUuid())
			)
			.statusCode(200);
		
		get("/users/"+u2.getId()+"/timeline")
			.then()
			.assertThat()
			.body(
				"size()", is(1), 
				"uuid", hasItems(c1.getUuid())
			)
			.statusCode(200);
		//@formatter:on
	}
	
	public void testGetNoFriends() {
		//@formatter:off
		post("/users/"+u1.getId()+"/friends/remove/"+u2.getId())
			.then()
			.statusCode(200);
		
		get("/users/"+u1.getId()+"/timeline")
			.then()
			.assertThat()
			.body("size()", is(0))
			.statusCode(200);
		
		get("/users/"+u2.getId()+"/timeline")
			.then()
			.assertThat()
			.body("size()", is(0))
			.statusCode(200);
		//@formatter:on
	}
	
	public void testGetWrongId() {
		//@formatter:off
		get("/users/9999/timeline")
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
		
		c1 = chirp();
		c2 = chirp();
		c3 = chirp();

		u1.addFriend(u2);
		
		c1.setAuthor(u1);
		c2.setAuthor(u2);
		c3.setAuthor(u2);
		
		entityManager.persist(u1);
		entityManager.persist(u2);
		entityManager.persist(c1);
		entityManager.persist(c2);
		entityManager.persist(c3);
	}

}
