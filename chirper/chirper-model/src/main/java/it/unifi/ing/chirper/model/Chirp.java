package it.unifi.ing.chirper.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="chirps")
public class Chirp extends BaseEntity{

	private String content;
	@ManyToOne
	@JoinColumn(name="author")
	private User author;
	private Chirp reference;
	@OneToMany(mappedBy="comment", cascade=CascadeType.REMOVE)
	private List<Comment> comments;
	
	public Chirp() {
		init();
	}
	
	private void init() {
		comments = new LinkedList<>();

	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
		author.addChirp(this);
	}
	
	public Chirp getReference() {
		return reference;
	}
	public void setReference(Chirp reference) {
		this.reference = reference;
	}
	public List<Comment> getComments() {
		return Collections.unmodifiableList( comments );
	}
	void addComment(Comment comment) {
		this.comments.add( comment );
	}
	
	
	
}
