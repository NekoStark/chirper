package it.unifi.ing.chirper.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="comments")
public class Comment extends BaseEntity{

	@ManyToOne @JoinColumn(name="author_id")
	private User author;
	
	@ManyToOne @JoinColumn(name="comment")
	private Chirp chirp;

	private String content;
	
	
	Comment() {}
	
	public Comment(String uuid) {
		this.setUuid(uuid);
	}
	
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Chirp getChirp() {
		return chirp;
	}
	public void setChirp(Chirp chirp) {
		this.chirp = chirp;
		chirp.addComment(this);
	}
	
}
