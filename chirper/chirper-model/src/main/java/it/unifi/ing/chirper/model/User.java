package it.unifi.ing.chirper.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import it.unifi.ing.chirper.model.utils.password.UserPasswordTools;

public class User {

	private String userName;
	private String email;
	private String password;
	private Set<User> friends;
	private List<Chirp> chirps;
	
	public User() {
		init();
	}

	private void init() {
		friends = new HashSet<>();
		chirps = new LinkedList<>();
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String plainPassword) {
		this.password = UserPasswordTools.encrypt( plainPassword );
	}
	public boolean checkPassword(String plainPassword) {
		return this.getPassword().equals( encrypt(plainPassword) );
	}
	private String encrypt(String plainPassword) {
		return UserPasswordTools.encrypt( plainPassword );
	}
	
	public Set<User> getFriends() {
		return Collections.unmodifiableSet( friends );
	}
	public void addFriend(User user) {
		this.friends.add(user);
		user.friends.add(this);
	}
	
	public List<Chirp> getChirps() {
		return Collections.unmodifiableList( chirps );
	}
	void addChirp(Chirp chirps) {
		this.chirps.add( chirps );
	}
	
}
