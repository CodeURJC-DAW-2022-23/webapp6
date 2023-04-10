package es.codeurjc.readmebookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.codeurjc.readmebookstore.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

        @Query(value = "SELECT * FROM book WHERE title = :title", nativeQuery = true)
        Optional<Book> findByTitle (String title);

        @Query(value = "SELECT * FROM book WHERE author = :author", nativeQuery = true)
        List<Book> findByAuthor (String author);

        @Query(value = "SELECT * FROM book  WHERE genre = :genre", nativeQuery = true)
        List<Book> findByGenre (String genre);
        
        @Query(value = "SELECT * FROM book  WHERE (genre LIKE %:partial%) OR (title LIKE  %:partial%) OR (author LIKE %:partial%)", nativeQuery = true)
        List<Book> findByPartial (String partial);

        @Query(value = "SELECT * FROM book  WHERE id = :id", nativeQuery = true)
        Book BookfindById (long id);

        @Query(value = "SELECT * FROM (SELECT * FROM book b WHERE b.id in (SELECT favourite_book_id FROM user_favourite_book f WHERE f.user_id = :userid)) c WHERE c.id = :bookid", nativeQuery = true)
        List<Book> isFavorite(Long userid, Long bookid);

        @Query(value = "SELECT * FROM book b WHERE b.id in (SELECT favourite_book_id FROM user_favourite_book f WHERE f.user_id = :userid)", nativeQuery = true)
        List<Book> favoritesbooks(Long userid);

        @Query(value = "SELECT * FROM book b WHERE b.id in (SELECT favourite_book_id FROM user_favourite_book f WHERE f.user_id = :userid)", nativeQuery = true)
        Page<Book> favoriteBooks(Long userid, Pageable page);
        
        public Page<Book> findAll(Pageable page);

        @Query(value = "SELECT * FROM book WHERE title = :title", nativeQuery = true)
        Page<Book> findPageTitle (String title, Pageable page);

        @Query(value = "SELECT * FROM book WHERE author = :author", nativeQuery = true)
        Page<Book> findPageAuthor (String author, Pageable page);

        @Query(value = "SELECT * FROM book WHERE genre = :genre", nativeQuery = true)
        Page<Book> findPageGenre (String genre, Pageable page);
        
        @Query(value = "SELECT * FROM book  WHERE (genre LIKE  %:partial%) OR (title LIKE  %:partial%) OR (author LIKE  %:partial%)", nativeQuery = true)
        Page<Book> findPagePartial (String partial, Pageable page);

}