package es.codeurjc.readmebookstore.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import es.codeurjc.readmebookstore.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String username);

    public Page<User> findAll(Pageable page);

    @Query(value = "SELECT * FROM books.user where name = :username", nativeQuery = true)
    Optional<User> findByNameopt(String username);

    @Modifying
    @Transactional
    @Query(value = "Delete from books.user_favourite_book where favourite_book_id = :id", nativeQuery = true)
    void deletefavorite(long id);
    
}