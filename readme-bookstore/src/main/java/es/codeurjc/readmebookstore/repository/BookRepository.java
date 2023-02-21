package es.codeurjc.readmebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.readmebookstore.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}