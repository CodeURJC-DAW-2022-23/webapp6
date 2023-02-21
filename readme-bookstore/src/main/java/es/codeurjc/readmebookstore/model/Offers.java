package es.codeurjc.readmebookstore.model;

import java.sql.Blob;
import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Table;

@Entity
@Table(name = "Offers")
@DynamicUpdate
public class Offers {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;
	
	private Date date;
	private String edition;
	private Float price;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Lob
	private Blob imageFile;

	private boolean image;

	@ManyToOne
	private Book book;

   @ManyToMany
	private List<User> users;


	public Offers() {}

	public Offers(Date date, String edition, String description, Float price) {
		this.date = date;
		this.edition = edition;
		this.description = description;
		this.price =price;
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

	public boolean getImage(){
		return this.image;
	}

	public void setImage(boolean image){
		this.image = image;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<User> getUsers() {
		return users;
	}
	
	public void setGames(List<User> users) {
		this.users = users;
	}	
}
