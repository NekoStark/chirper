package it.unifi.ing.chirper.services.endpoints;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.unifi.ing.chirper.dao.UserDao;
import it.unifi.ing.chirper.model.User;

@Path("/users")
public class UserFriendEndpoint {

	@Inject
	private UserDao userDao;
	
	@GET
	@Path("/{id}/friends")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response query(@PathParam("id") Long userId) {
		User result = userDao.findById(userId);

		if (result == null) {
			return Response.status(404).build();
		}

		return Response.status(200).entity(result.getFriends()).build();
	}
	
	@POST
	@Path("/{id}/friends/add/{aid}")
	@Transactional
	public Response add(@PathParam("id") Long userId, @PathParam("aid") Long otherUserId) {
		User result = userDao.findById(userId);
		User candidateFriend = userDao.findById(otherUserId);

		if (result == null || candidateFriend == null) {
			return Response.status(404).build();
		}

		result.addFriend(candidateFriend);
		
		return Response.status(200).build();
	}
	
	@POST
	@Path("/{id}/friends/remove/{aid}")
	@Transactional
	public Response remove(@PathParam("id") Long userId, @PathParam("aid") Long otherUserId) {
		User result = userDao.findById(userId);
		User candidateFriend = userDao.findById(otherUserId);

		if (result == null || candidateFriend == null) {
			return Response.status(404).build();
		}

		result.removeFriend(candidateFriend);
		
		return Response.status(200).build();
	}
	
}
