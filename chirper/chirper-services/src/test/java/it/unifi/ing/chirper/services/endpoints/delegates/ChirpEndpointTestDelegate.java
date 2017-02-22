package it.unifi.ing.chirper.services.endpoints.delegates;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static it.unifi.ing.chirper.model.factory.ModelFactory.chirp;
import static it.unifi.ing.chirper.model.factory.ModelFactory.comment;
import static it.unifi.ing.chirper.model.factory.ModelFactory.user;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import javax.persistence.EntityManager;

import it.unifi.ing.chirper.model.Chirp;
import it.unifi.ing.chirper.model.Comment;
import it.unifi.ing.chirper.model.User;

public class ChirpEndpointTestDelegate {
	private EntityManager entityManager;

	private Chirp c1;
	private User u1;
	private Comment com1;
	private Comment com2;

	public void init(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void insertData() {
		c1 = chirp();
		c1.setContent("content1");

		u1 = user();
		c1.setAuthor(u1);

		com1 = comment();
		com1.setAuthor(u1);
		com1.setContent("content");
		com1.setChirp(c1);

		com2 = comment();
		com2.setAuthor(u1);
		com2.setContent("content");
		com2.setChirp(c1);


		entityManager.persist(c1);
		entityManager.persist(u1);
		entityManager.persist(com1);
		entityManager.persist(com2);
	}


	public void testGet(){
		get("/chirp/"+c1.getId()).
		then().
		assertThat().
		body(
				"uuid", equalTo(c1.getUuid()),
				"content", equalTo(c1.getContent())).
		statusCode(200);


		get("/chirp/999").
		then().
		assertThat().
		statusCode(404);
	}

	public void testNew(){
		long chirpId = given().
				header("userId", u1.getId()).
				header("content", "content").
				when().
				post("/chirp").
				then().
				extract().
				jsonPath().getLong("id");


		get("/chirp/"+chirpId).
		then().
		assertThat().
		body(
				"uuid", notNullValue(),
				"content", equalTo("content"),
				"author.uuid", is(u1.getUuid())).
		statusCode(200);



		given().
		header("userId", 999).
		header("content", "content").
		when().
		post("/chirp").
		then().
		assertThat().
		statusCode(404);


		given().
		header("userId", u1.getId()).
		header("content", "").
		when().
		post("/chirp").
		then().
		assertThat().
		statusCode(500);

	}

	public void testSet(){
		given().
		header("content", "newContent").
		when().
		put("/chirp/" + c1.getId());


		get("/chirp/" +c1.getId()).
		then().
		assertThat().
		body(
				"content", equalTo("newContent")).
		statusCode(200);


		given().
		header("content", "").
		when().
		put("/chirp/" + c1.getId()).
		then().
		assertThat().
		statusCode(500);

	}

	public void testDel(){
		given().
		when().
		delete("/chirp/" +c1.getId());


		given().
		when().
		delete("/chirp/" +c1.getId()).
		then().
		statusCode(404);

	}

}
