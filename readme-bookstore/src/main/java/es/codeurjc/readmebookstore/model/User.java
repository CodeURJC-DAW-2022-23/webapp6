package es.codeurjc.readmebookstore.model;

import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import java.sql.Blob;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@DynamicUpdate

public class User {

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", email=" + email
				+ ", imageFile=" + imageFile + ", image=" + image + ", encodedPassword=" + encodedPassword +
				", roles=" + roles + ", readedReviews=" + readedReview + "]";
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private String email;

	private String encodedPassword;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;

	@Lob
	private Blob imageFile;
	private boolean image;

	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Review> readedReview;

	@OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Offer> offer;

	@ManyToMany
	private List<Book> favouriteBook;

	public User() {
	}

	public User(String name, String encodedPassword, String email, String... roles) {
		this.name = name;
		this.encodedPassword = encodedPassword;
		this.email = email;
		this.roles = List.of(roles);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getEncodedPassword() {
		return encodedPassword;
	}

	public List<String> getRoles() {
		return roles;
	}

	public List<Review> getReadedReviews() {
		return readedReview;
	}

	public List<Book> getFavouriteBooks() {
		return favouriteBook;
	}

	public List<Offer> getOffers() {
		return offer;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEncodedPassword(String encodedPassword) {
		this.encodedPassword = encodedPassword;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public void setReadedReviews(List<Review> readedReview) {
		this.readedReview = readedReview;
	}

	public void setFavouriteBooks(Book favouriteBook) {
		this.favouriteBook.add((Book) favouriteBook);
	}

	public void setOffers(List<Offer> offer) {
		this.offer = offer;
	}

	public Blob getImageFile() {
		return imageFile;
	}

	public void setImageFile(Blob image) {
		this.imageFile = image;
	}

	public boolean hasImage() {
		return this.image;
	}

	public void setImage(boolean image) {
		this.image = image;
	}

	public boolean getImage() {
		return image;
	}

}