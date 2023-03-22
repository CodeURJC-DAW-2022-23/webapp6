package es.codeurjc.readmebookstore.model;

import java.sql.Blob;
import java.util.Date;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	private User seller;

	@JsonInclude(Include.NON_NULL)
	@ManyToOne
	private User buyer;

	public Offer() {}

	public Offer (String edition, String description, Float price){
		this.edition = edition;
		this.description = description;
		this.price = price;
	}

	public Offer(Date date, String edition, String description, Float price, Book book, User seller, Boolean sold, User buyer) {
		this.date = date;
		this.edition = edition;
		this.description = description;
		this.price = price;
		this.book = book;
		this.seller = seller;
		this.sold = sold;
		this.buyer = buyer;
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

	@JsonIgnore
	public Blob getImageFile() {
		return imageFile;
	}

	public void setImageFile(Blob image) {
		this.imageFile = image;
	}

	@JsonIgnore
	public boolean getImage() {
		return this.image;
	}

	public void setImage(boolean image) {
		this.image = image;
	}

	public boolean hasImage() {
		return this.image;
	}

	public boolean getSold() {
		return this.sold;
	}

	public void setSold(boolean sold) {
		this.sold= sold;
	}

	@JsonIgnoreProperties(value = {"imageFile", "image", "offer", "review", "user", "categories"})
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@JsonIgnoreProperties(value = {"roles", "image", "encodedPassword", "imageFile", "readedReviews", "offers", "favouriteBooks"})
	public User getSeller() {
		return seller;
	}

	public void setSeller(User user) {
		this.seller = user;
	}

	@JsonIgnoreProperties(value = {"roles", "image", "encodedPassword", "imageFile", "readedReviews", "offers", "favouriteBooks"})
	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User user) {
		this.buyer = user;
	}
}
