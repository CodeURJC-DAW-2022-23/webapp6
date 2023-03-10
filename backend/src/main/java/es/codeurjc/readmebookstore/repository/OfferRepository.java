package es.codeurjc.readmebookstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import es.codeurjc.readmebookstore.model.Offer;
import org.springframework.data.jpa.repository.Query;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Query(value = "SELECT * FROM OFFERS WHERE (SOLD= 0) AND (BOOK_ID = :id)", nativeQuery = true)
    Page<Offer> findByOffersNotSoldByBook(long id, Pageable pageable);

    @Query(value = "SELECT * FROM OFFERS WHERE (SOLD= 0) AND (SELLER_ID = :id)", nativeQuery = true)
    List<Offer> findByOffersNotSoldByUser(long id);

    @Query(value = "SELECT * FROM OFFERS WHERE (SOLD= 0) AND (SELLER_ID = :id)", nativeQuery = true)
    Page<Offer> findByOffersNotSoldByUser(long id, Pageable page);

    @Query(value = "SELECT * FROM OFFERS WHERE (SOLD= 1) AND ((SELLER_ID = :id) OR (BUYER_ID = :id))", nativeQuery = true)
    Page<Offer> findByShoppingHistorial(long id, Pageable page);

    @Query(value = "SELECT * FROM books.offers where buyer_id = :id", nativeQuery = true)
    List<Offer> findBooksBought(long id);

    public Page<Offer> findAll(Pageable page);

}
