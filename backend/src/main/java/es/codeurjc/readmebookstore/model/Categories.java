package es.codeurjc.readmebookstore.model;

import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToMany;

@Entity
@DynamicUpdate
@Table(name = "Categories")
public class Categories {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;

	private String categorie;

	@ManyToMany
	private List<Book> books;

	public Categories() {
	}

	public Categories(String categorie) {
		this.categorie = categorie;
	}

    public void setId(long id) {
		this.id = id;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

    public void setBooks(List<Book> books) {
		this.books = books;
	}

    public Long getId() {
		return id;
	}
    
    public String getCategorie() {
		return categorie;
	}

    public List<Book> getBooks() {
		return books;
	}

}