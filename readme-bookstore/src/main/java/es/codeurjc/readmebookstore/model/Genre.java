/*package es.codeurjc.readmebookstore.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Genre")
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;
	
	private String name;

	@ManyToMany
 	private List<Book> books;

	public Genre() {}

	public Genre(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Book> getBook() {
		return books;
	}

	public void setShops(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", books=" + books + "]";
	}
}
*/