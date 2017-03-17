package it.unifi.ing.chirper.services.endpoints;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.unifi.ing.chirper.dao.UserDao;
import it.unifi.ing.chirper.model.Chirp;
import it.unifi.ing.chirper.model.User;

@Path("/users")
public class UserTimelineEndpoint {

	@Inject
	private UserDao userDao;
	
	@GET
	@Path("/{id}/timeline")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response get(@PathParam("id") Long userId) {
		User user = userDao.findById(userId);

		if (user == null) {
			return Response.status(404).build();
		}

		List<Chirp> result = new LinkedList<>();
		for(User friend : user.getFriends()) {
			result.addAll(friend.getChirps());
		}
		
		return Response.status(200).entity(result).build();
	}
	
}
