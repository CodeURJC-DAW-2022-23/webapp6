package es.codeurjc.readmebookstore.model;

import java.sql.Blob;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToMany;

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
	 private Blob imageFile;
  
	 private boolean image;

	@OneToMany(mappedBy="book", cascade=CascadeType.ALL, orphanRemoval=true)
 	private List<Offer> offer;

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

	public Blob getImageFile() {
		return imageFile;
	}

	public void setImageFile(Blob image) {
		this.imageFile = image;
	}

	public boolean getImage() {
		return this.image;
	}

	public void setImage(boolean image) {
		this.image = image;
	}

	public List<Offer> getOffers() {
		return offer;
	}

	public void setOffers(List<Offer> offer) {
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
