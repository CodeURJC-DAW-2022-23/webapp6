package es.codeurjc.readmebookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.codeurjc.readmebookstore.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

        @Query(value = "select * from books.book  where title = :title", nativeQuery = true)
        Optional<Book> findByTitle (String title);

        @Query(value = "select * from books.book  where author = :author", nativeQuery = true)
        List<Book> findByAuthor (String author);

        @Query(value = "select * from books.book  where genre = :genre", nativeQuery = true)
        List<Book> findByGenre (String genre);
        
        @Query(value = "select * from books.book  where (genre like  %:partial%) or (title like  %:partial%) or (author like  %:partial%);", nativeQuery = true)
        List<Book> findByPartial (String partial);

        @Query(value = "select * from books.book  where id = :id", nativeQuery = true)
        Book BookfindById (long id);

        @Query(value = "SELECT * FROM (SELECT * FROM books.book b where b.id in (select favourite_book_id from books.user_favourite_book f  where f.user_id = :userid)) c WHERE c.id = :bookid", nativeQuery = true)
        List<Book> isFavorite(Long userid, Long bookid);

        @Query(value = "SELECT * FROM books.book b where b.id in (select favourite_book_id from books.user_favourite_book f  where f.user_id = :userid)", nativeQuery = true)
        List<Book> favoritesbooks(Long userid);
        
        public Page<Book> findAll(Pageable page);

}