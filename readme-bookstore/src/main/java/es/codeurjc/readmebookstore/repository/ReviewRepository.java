package es.codeurjc.readmebookstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.codeurjc.readmebookstore.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "SELECT * FROM REVIEWS where book_id = :bookid",nativeQuery = true)
    Page<Review> findByAllReviewsByBook(long bookid, Pageable pageable);

    @Query(value = "SELECT * FROM REVIEWS where user_id = :userid",nativeQuery = true)
    Page<Review> findByAllReviewsByUser(long userid, Pageable pageable);

}
