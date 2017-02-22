package it.unifi.ing.chirper.services.endpoints;

import javax.inject.Inject;
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

import it.unifi.ing.chirper.dao.ChirpDao;
import it.unifi.ing.chirper.dao.CommentDao;
import it.unifi.ing.chirper.dao.UserDao;
import it.unifi.ing.chirper.model.Chirp;
import it.unifi.ing.chirper.model.Comment;
import it.unifi.ing.chirper.model.User;
import it.unifi.ing.chirper.model.factory.ModelFactory;

@Path("/chirp")
public class CommentEndpoint {

	@Inject
	private ChirpDao chirpDao;
	
	@Inject
	private UserDao userDao;
	
	@Inject
	private CommentDao commentDao;
	
	@GET
	@Path("/{id}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getComment(@PathParam("id")Long chirpId){
		try{
			Chirp chirp = chirpDao.findById(chirpId);
			return Response.status(200).entity(chirp.getComments()).build();
		}catch(Exception e){
			return Response.status(404).build();
		}
		
	}
	
	@POST
	@Path("/{id}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public Response newComment(@PathParam("id")Long chirpId, @HeaderParam("userId")Long userId, @HeaderParam("content") String content){
		try{
			Chirp chirp = chirpDao.findById(chirpId);
			User author = userDao.findById(userId);
			
			Comment comment = ModelFactory.comment();
			commentDao.save(comment);
			
			comment.setAuthor(author);
			comment.setChirp(chirp);
			return Response.status(200).entity(comment).build();
		}catch (Exception e) {
			return Response.status(404).build();
		}
		
	}
	
	
	@PUT
	@Path("/{id}/comments/{cid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response editComment(@PathParam("id")Long chirpId, @PathParam("cid")Long commentId, @HeaderParam("content") String content){
		if(content == null){
			return Response.status(404).build();
		}
		try{
			Comment comment = commentDao.findById(commentId);
			comment.setContent(content);
			return Response.status(200).build();
		}catch (Exception e) {
			return Response.status(404).build();
		}
	}
	
	@DELETE
	@Path("/{id}/comments/{cid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteComment(@PathParam("id")Long chirpId, @PathParam("cid")Long commentId){
		try{
			commentDao.delete(commentId);
			return Response.status(200).build();
		}catch (Exception e) {
			return Response.status(400).build();
		}
	}
}
