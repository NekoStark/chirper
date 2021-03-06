package it.unifi.ing.chirper.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "chirps")
public class Chirp extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "author")
	private User author;

	@ManyToOne
	@JoinColumn(name = "reference")
	private Chirp reference;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "chirp", cascade = CascadeType.REMOVE)
	private List<Comment> comments;

	private String content;

	Chirp() {
		init();
	}

	public Chirp(String uuid) {
		super(uuid);
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

	@JsonIgnore
	public List<Comment> getComments() {
		return Collections.unmodifiableList(comments);
	}

	public void removeComment(Comment comment) {
		comments.remove(comment);
	}

	void addComment(Comment comment) {
		this.comments.add(comment);
	}

}
