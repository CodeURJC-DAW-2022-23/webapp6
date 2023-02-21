package es.codeurjc.readmebookstore.model;

import java.sql.Date;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Review")
@DynamicUpdate
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;

    @Column(columnDefinition = "TEXT")
	private String text;

	@Temporal(TemporalType.DATE)
    private Date date;

	@ManyToOne
 	private Book book;

    @ManyToOne
    private User user;

    public Review() {}

	public Review(String text, Date date) {
		super();
		this.text = text;
		this.date = date;
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

	@Override
	public String toString() {
		return "Review [id=" + id + ", texto=" + text + ", fecha=" + date + "]";
	}
}
