package es.codeurjc.readmebookstore.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.codeurjc.readmebookstore.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "SELECT * FROM reviews WHERE book_id = :bookid", nativeQuery = true)
    List<Review> findByAllReviewsByBook(long bookid);

    @Query(value = "SELECT * FROM reviews WHERE book_id = :bookid", nativeQuery = true)
    Page<Review> findByAllReviewsByBook(long bookid, Pageable pageable);

    @Query(value = "SELECT * FROM reviews WHERE author_id = :userid", nativeQuery = true)
    Page<Review> findByAllReviewsByUser(long userid, Pageable pageable);

    @Query(value = "SELECT * FROM reviews WHERE author_id = :userid", nativeQuery = true)
    List<Review> findByAllReviewsByUser(long userid);

    public Page<Review> findAll(Pageable page);
}
