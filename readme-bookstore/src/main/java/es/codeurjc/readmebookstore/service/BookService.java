package es.codeurjc.readmebookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import es.codeurjc.readmebookstore.model.Book;
import es.codeurjc.readmebookstore.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;

	public Optional<Book> findById(long id) {
		return repository.findById(id);
	}
	
	public boolean exist(long id) {
		return repository.existsById(id);
	}

	public Page<Book> findAll(int n) {
		return repository.findAll( PageRequest.of(n, 5));
	}

	public List<Book> findAll() {
		return repository.findAll();
	}

	public void save(Book book) {
		repository.save(book);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}

	public Optional<Book> findByTitle (String title) {
		return repository.findByTitle(title);
	}

	public List<Book> findByAuthor (String author) {
		return repository.findByAuthor(author);
	}

	public List<Book> findByGenre (String genre) {
		return repository.findByGenre(genre);
	}

	public List<Book> findByPartial (String genre) {
		return repository.findByPartial(genre);
	}

	public Book BookfindById (long id) {
		return repository.BookfindById(id);
	}

	public List<Book> isFavorite (Long userid, Long bookid) {
		return repository.isFavorite(userid, bookid);
	}

}
