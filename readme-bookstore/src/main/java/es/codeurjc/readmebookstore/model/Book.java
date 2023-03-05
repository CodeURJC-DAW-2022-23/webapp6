package es.codeurjc.readmebookstore.model;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.ManyToMany;

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

	@Lob
	private Blob imageFile;

	private boolean image;

	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Offer> offer;

	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Review> review;

	@ManyToMany
	private List<User> user;

	@ManyToMany
	private List<Categories> categories;

	public Book() {
	}

	public Book(String title, String author, String genre) {
		super();
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.categories = new ArrayList<>();
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

	@JsonIgnore
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

	@JsonIgnore
	public List<Offer> getOffers() {
		return offer;
	}

	public void setOffers(List<Offer> offer) {
		this.offer = offer;
	}

	@JsonIgnore
	public List<Review> getReviews() {
		return review;
	}

	public void setReviews(List<Review> review) {
		this.review = review;
	}

	@JsonIgnore
	public List<User> getUsers() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public List<Categories> getCategories() {
		return categories;
	}

	public void setCategories(List<Categories> categories) {
		this.categories = categories;
	}
	
	public void setCategories(Categories categories) {
		this.categories.add((Categories) categories);
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + "]";
	}
}
