package es.codeurjc.readmebookstore.model;

import java.sql.Blob;
import java.util.Date;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Offers")
@DynamicUpdate
public class Offer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;

	@Temporal(TemporalType.DATE)
	private Date date;
	
	private String edition;

	private String description;

	private Float price;

	@Lob
	private Blob imageFile;

	private boolean image;

	private boolean sold;

	@ManyToOne
	private Book book;

	@ManyToOne
	private User user;

	public Offer() {
	}

	public Offer(Date date, String edition, String description, Float price, Book book, User user) {
		this.date = date;
		this.edition = edition;
		this.description = description;
		this.price = price;
		this.book = book;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

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

	public boolean getSold() {
		return this.sold;
	}

	public void setSold(boolean sold) {
		this.sold= sold;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
