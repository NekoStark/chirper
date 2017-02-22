package it.unifi.ing.chirper.services.endpoints;

import javax.inject.Inject;
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

import it.unifi.ing.chirper.dao.UserDao;
import it.unifi.ing.chirper.model.User;
import it.unifi.ing.chirper.model.factory.ModelFactory;

@Path("/users")
public class UserEndpoint {

	@Inject
	private UserDao userDao;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response query() {
		return Response.status(200).entity(userDao.allUser()).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response newUser(@HeaderParam("username") String username, @HeaderParam("email") String email, @HeaderParam("password") String password) {		
		if(username == null || email == null || password == null) {
			return Response.status(404).build();
		}
		
		User result = ModelFactory.user();
		result.setUserName(username);
		result.setEmail(email);
		result.setPassword(password);
		userDao.save(result);
		return Response.status(200).entity(result).build();
		
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id")Long userId) {
		
		User result = userDao.findById(userId);
		
		if(result == null){
			return Response.status(404).build();
		}
		
		
		return Response.status(200).entity(result).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response set(@PathParam("id")Long userId, @HeaderParam("username") String username, @HeaderParam("email") String email, @HeaderParam("password") String password) {
		User result = userDao.findById(userId);
		
		if(username != null){
			result.setUserName(username);
		}
		if(email != null){
			result.setEmail(email);
		}
		if( password != null){
			result.setPassword(password);
		}
		
		return Response.status(200).entity(result).build();
	}
	
	
	
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id")Long userId) {
		try{
			userDao.delete(userId);	
		}catch (Exception e) {
			return Response.status(404).build();
		}
		return Response.status(200).build();
	}
	
}
