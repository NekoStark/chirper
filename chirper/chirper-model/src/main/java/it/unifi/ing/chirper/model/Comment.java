package it.unifi.ing.chirper.model;

public class Comment {

	private User author;
	private String content;
	private Chirp chirp;
	
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
