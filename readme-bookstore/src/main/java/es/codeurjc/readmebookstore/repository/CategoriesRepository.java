package es.codeurjc.readmebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.readmebookstore.model.Categories;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {

}
