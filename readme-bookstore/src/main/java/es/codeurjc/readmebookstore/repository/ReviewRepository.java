package es.codeurjc.readmebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.readmebookstore.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
