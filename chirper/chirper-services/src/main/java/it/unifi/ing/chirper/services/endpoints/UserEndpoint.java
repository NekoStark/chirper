package it.unifi.ing.chirper.services.endpoints;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import it.unifi.ing.chirper.dao.UserDao;
import it.unifi.ing.chirper.model.User;

@Path("/users")
public class UserEndpoint {

	@Inject
	private UserDao userDao;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> query() {
		//TODO far un metodo nel DAO che ritorna tutti gli utenti
		return Collections.EMPTY_LIST;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User get(@PathParam("id")Long userId) {
		return userDao.findById(userId);
	}
	
}
