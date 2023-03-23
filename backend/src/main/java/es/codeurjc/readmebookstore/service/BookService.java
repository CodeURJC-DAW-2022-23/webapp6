package es.codeurjc.readmebookstore.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
		return repository.findAll(PageRequest.of(n, 4));
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

	public Optional<Book> findByTitle(String title) {
		return repository.findByTitle(title);
	}

	public List<Book> findByAuthor(String author) {
		return repository.findByAuthor(author);
	}

	public List<Book> findByGenre(String genre) {
		return repository.findByGenre(genre);
	}

	public List<Book> findByPartial(String genre) {
		return repository.findByPartial(genre);
	}

	public Book BookfindById(long id) {
		return repository.BookfindById(id);
	}

	public List<Book> isFavorite(Long userid, Long bookid) {
		return repository.isFavorite(userid, bookid);
	}

	public List<Book> favoritesbooks(Long userid) {
		return repository.favoritesbooks(userid);
	}

	public Page<Book> favoriteBooks(Long userid, int n) {
		return repository.favoriteBooks(userid, PageRequest.of(n, 4));
	}
	
	public Page<Book> findPageAuthor(String author, int n) {
		return repository.findPageAuthor(author, PageRequest.of(n, 4));
	}

	public Page<Book> findPageGenre(String genre, int n) {
		return repository.findPageGenre(genre, PageRequest.of(n, 4));
	}

	public Page<Book> findPagePartial(String genre, int n) {
		return repository.findPagePartial(genre, PageRequest.of(n, 4));
	}

	public void updateImage(Book book, boolean removeImage, MultipartFile imageField)
            throws IOException, SQLException {
        if (!imageField.isEmpty()) {
            book.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
            book.setImage(true);
        } else {
            if (removeImage) {
                book.setImageFile(null);
                book.setImage(false);
            } else {
                Book dbBook = findById(book.getId()).orElseThrow();
                if (dbBook.hasImage()) {
                    book.setImageFile(BlobProxy.generateProxy(dbBook.getImageFile().getBinaryStream(),
                            dbBook.getImageFile().length()));
                    book.setImage(true);
                }
            }
        }
    }

	public Boolean doSearchTitle(String title) {
		Boolean result = false;
		try {
			Optional<Book> booktitle = repository.findByTitle(title);
			if (booktitle.get().getId() != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public Boolean doSearchAuthor(String author) {
		Boolean result = false;
		try {
			List<Book> bookauthor = repository.findByAuthor(author);
			if (bookauthor.size() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public Boolean doSearchGenre(String genre) {
		Boolean result = false;
		try {
			List<Book> bookgenre = repository.findByGenre(genre);
			if (bookgenre.size() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public Boolean doSearchPartial(String partial) {
		Boolean result = false;
		try {
			List<Book> bookpartial = repository.findByPartial(partial);
			if (bookpartial.size() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
}
