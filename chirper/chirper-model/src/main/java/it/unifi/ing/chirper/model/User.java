package it.unifi.ing.chirper.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.unifi.ing.chirper.model.utils.password.UserPasswordTools;

@Entity
@Table(name="users")
public class User extends BaseEntity{

	@ManyToMany @JoinTable(name="friends", joinColumns=@JoinColumn(name="user_id"))
	private Set<User> friends;
	
	@OneToMany(mappedBy="author", cascade=CascadeType.REMOVE)
	private List<Chirp> chirps;
	
	private String userName;
	private String email;
	private String password;
	
	User() {
		init();
	}
	public User(String uuid) {
		this.setUuid(uuid);
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
	
	public void removeFriend(User user){
		friends.remove(user);

	}

	public void clearFriends(){
		friends.clear();
	}
	public List<Chirp> getChirps() {
		return Collections.unmodifiableList( chirps );
	}
	public void removeChirp(Chirp chirp){
		chirps.remove(chirp);
	}
	public void clearChirp(){
		chirps.clear();
	}
	void addChirp(Chirp chirp) {
		this.chirps.add( chirp );
	}
	
}
