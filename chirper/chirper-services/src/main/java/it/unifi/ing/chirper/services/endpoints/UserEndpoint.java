package it.unifi.ing.chirper.services.endpoints;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import it.unifi.ing.chirper.dao.UserDao;
import it.unifi.ing.chirper.model.User;
import it.unifi.ing.chirper.model.factory.ModelFactory;

@Path("/users")
public class UserEndpoint {

	@Inject
	private UserDao userDao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response query() {
		return Response.status(200).entity(userDao.allUser()).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response get(@PathParam("id") Long userId) {
		User result = userDao.findById(userId);

		if (result == null) {
			return Response.status(404).build();
		}

		return Response.status(200).entity(result).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response add(@HeaderParam("username") String username, @HeaderParam("email") String email,
			@HeaderParam("password") String password) {
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(email)) {
			return Response.status(500).build();
		}

		User result = ModelFactory.user();
		result.setUserName(username);
		result.setEmail(email);
		result.setPassword(password);
		userDao.save(result);

		return Response.status(200).entity(result).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response update(@PathParam("id") Long userId, @HeaderParam("username") String username,
			@HeaderParam("email") String email, @HeaderParam("password") String password) {
		User result = userDao.findById(userId);
		if (result == null) {
			return Response.status(404).build();
		}
		if (!StringUtils.isEmpty(username)) {
			result.setUserName(username);
		}
		if (!StringUtils.isEmpty(email)) {
			result.setEmail(email);
		}
		if (!StringUtils.isEmpty(password)) {
			result.setPassword(password);
		}

		return Response.status(200).entity(result).build();
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public Response delete(@PathParam("id") Long userId) {
		User user = userDao.findById(userId);
		if (user == null) {
			return Response.status(404).build();
		}
		userDao.delete(user);
		return Response.status(200).build();
	}

}
