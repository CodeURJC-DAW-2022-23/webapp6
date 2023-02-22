package es.codeurjc.readmebookstore.model;

import java.sql.Blob;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;
import jakarta.annotation.Nonnull;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToMany;

import jakarta.persistence.Table;

@Entity
@DynamicUpdate
@Table(name = "Book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;
	
	private String title;

	private String author;

	private String genre;
	/*@ManyToMany
 	private List<Genre> genre;*/
	
	@Lob
	private Blob cover;

	@OneToMany(mappedBy="book", cascade=CascadeType.ALL, orphanRemoval=true)
 	private List<Offers> offer;

	@OneToMany(mappedBy="book", cascade=CascadeType.ALL, orphanRemoval=true)
 	private List<Review> review;

	@ManyToMany
	private List<User> user;

	public Book() {}

	public Book(String title, String author, String genre) {
		super();
		this.title = title;
		this.author = author;
		this.genre = genre;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	/*public List<Genre> getGenre() {
		return genre;
	}

	public void setGenre(List<Genre> genre) {
		this.genre = genre;
	}*/

	public Blob getCover() {
		return cover;
	}

	public void setCover(Blob image) {
		this.cover = image;
	}

	public List<Offers> getOffers() {
		return offer;
	}

	public void setOffers(List<Offers> offer) {
		this.offer = offer;
	}

	public List<Review> getReviews() {
		return review;
	}

	public void setReviews(List<Review> review) {
		this.review = review;
	}

	public List<User> getUsers() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + "]";
	}
}
