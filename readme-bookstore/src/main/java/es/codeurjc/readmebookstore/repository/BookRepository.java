package es.codeurjc.readmebookstore.repository;

import java.util.List;
import java.util.Optional;

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

}