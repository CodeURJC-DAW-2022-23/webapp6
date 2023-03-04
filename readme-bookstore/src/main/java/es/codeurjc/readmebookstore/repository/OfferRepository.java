package es.codeurjc.readmebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import es.codeurjc.readmebookstore.model.Offer;
import org.springframework.data.jpa.repository.Query;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Query(value = "SELECT * FROM OFFERS WHERE (SOLD= 0) AND (BOOK_ID = :id) ",nativeQuery = true)
    List<Offer> findByOffersNotSoldByBook(long id);

    @Query(value = "SELECT * FROM OFFERS WHERE (SOLD= 0) AND (SELLER_ID = :id) ",nativeQuery = true)
    List<Offer> findByOffersNotSoldByUser(long id);

    @Query(value = "SELECT * FROM OFFERS WHERE (SOLD= 1) AND ((SELLER_ID = :id) OR (BUYER_ID = :id))",nativeQuery = true)
    List<Offer> findByShoppingHistorial(long id);

    @Query(value = "SELECT * FROM books.offers where buyer_id = :id;",nativeQuery = true)
    List<Offer> findBooksBought(long id);
    
}




