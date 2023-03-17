package es.codeurjc.readmebookstore.model;

import java.util.Date;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Reviews")
@DynamicUpdate
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;

	private String text;

	@Temporal(TemporalType.DATE)
    private Date date;

	@ManyToOne
 	private Book book;

    @ManyToOne
    private User author;

    public Review() {}

	public Review(String text, Date date, Book book, User author) {
		this.text = text;
		this.date = date;
		this.book = book;
		this.author = author;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

    public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@JsonIgnoreProperties(value = {"imageFile", "image", "offer", "review", "user", "categories"})
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@JsonIgnoreProperties(value = {"roles", "image", "encodedPassword", "imageFile", "readedReviews", "offers", "favouriteBooks"})
	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", texto=" + text + ", fecha=" + date + "]";
	}
}
