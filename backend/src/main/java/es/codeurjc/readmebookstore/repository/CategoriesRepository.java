package es.codeurjc.readmebookstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.codeurjc.readmebookstore.model.Categories;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {

    @Query(value = "SELECT * FROM books.categories where categorie = :name", nativeQuery = true)
    Categories findByName (String name);

}
