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

import org.apache.commons.lang3.StringUtils;

import it.unifi.ing.chirper.dao.ChirpDao;
import it.unifi.ing.chirper.dao.UserDao;
import it.unifi.ing.chirper.model.Chirp;
import it.unifi.ing.chirper.model.User;
import it.unifi.ing.chirper.model.factory.ModelFactory;

@Path("/chirp")
public class ChirpEndpoint {

	@Inject
	private ChirpDao chirpDao;

	@Inject
	private UserDao userDao;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response newChirp(@HeaderParam("userId") Long userId,@HeaderParam("content") String content ) {		
		if(StringUtils.isEmpty(content)){
			return Response.status(500).build();
		}
		User author = userDao.findById(userId);
		if(author == null){
			return Response.status(404).build();
		}

		Chirp chirp = ModelFactory.chirp();
		chirp.setAuthor(author);
		chirp.setContent(content);
		chirpDao.save(chirp);
		return Response.status(200).entity(chirp).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id")Long chirpId) {
		Chirp result = chirpDao.findById(chirpId);
		if(result == null){		
			return Response.status(404).build();
		}
		return Response.status(200).entity(result).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response set(@PathParam("id")Long chirpId,@HeaderParam("content") String content) {
		if(StringUtils.isEmpty(content)){
			return Response.status(500).build();
		}

		Chirp chirp = chirpDao.findById(chirpId);
		if(chirp == null){
			return Response.status(404).build();
		}
		chirp.setContent(content);

		return Response.status(200).build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id")Long chirpId) {
		Chirp chirp = chirpDao.findById(chirpId);
		
		if(chirp == null){
			return Response.status(404).build();
			
		}
		chirpDao.delete(chirp);
		return Response.status(200).build();

	}

}
