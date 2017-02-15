package it.unifi.ing.chirper.model.factory;

import java.util.UUID;

import it.unifi.ing.chirper.model.Chirp;
import it.unifi.ing.chirper.model.Comment;
import it.unifi.ing.chirper.model.User;

public final class ModelFactory {

	private ModelFactory(){}
	
	public static User user() {
		return new User(UUID.randomUUID().toString());
	}
	
	public static Chirp chirp() {
		return new Chirp(UUID.randomUUID().toString());
	}
	
	public static Comment comment() {
		return new Comment();
	}
	
	
}
