package it.unifi.ing.chirper.services.endpoints;

import static it.unifi.ing.chirper.model.factory.ModelFactory.comment;

import javax.inject.Inject;
import javax.transaction.Transactional;
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
import it.unifi.ing.chirper.dao.CommentDao;
import it.unifi.ing.chirper.dao.UserDao;
import it.unifi.ing.chirper.model.Chirp;
import it.unifi.ing.chirper.model.Comment;
import it.unifi.ing.chirper.model.User;

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
	@Transactional
	public Response query(@PathParam("id") Long chirpId) {
		Chirp chirp = chirpDao.findById(chirpId);
		if (chirp == null) {
			return Response.status(404).build();
		}
		return Response.status(200).entity(chirp.getComments()).build();
	}

	@POST
	@Path("/{id}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response add(@PathParam("id") Long chirpId, @HeaderParam("userId") Long userId,
			@HeaderParam("content") String content) {
		if (StringUtils.isEmpty(content)) {
			return Response.status(500).build();
		}

		Chirp chirp = chirpDao.findById(chirpId);
		User author = userDao.findById(userId);

		if (chirp == null || author == null) {
			return Response.status(404).build();
		}

		Comment comment = comment();
		commentDao.save(comment);

		comment.setAuthor(author);
		comment.setChirp(chirp);
		return Response.status(200).entity(comment).build();

	}

	@PUT
	@Path("/{id}/comments/{cid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response update(@PathParam("id") Long chirpId, @PathParam("cid") Long commentId,
			@HeaderParam("content") String content) {
		if (StringUtils.isEmpty(content)) {
			return Response.status(500).build();
		}

		Chirp chirp = chirpDao.findById(chirpId);
		Comment comment = commentDao.findById(commentId);

		if (chirp == null || comment == null) {
			return Response.status(404).build();
		}

		comment.setContent(content);
		return Response.status(200).entity(comment).build();
	}

	@DELETE
	@Path("/{id}/comments/{cid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response delete(@PathParam("id") Long chirpId, @PathParam("cid") Long commentId) {
		Comment comment = commentDao.findById(commentId);
		if (comment == null) {
			return Response.status(404).build();
		}
		commentDao.delete(comment);
		return Response.status(200).build();
	}
}
