package es.codeurjc.readmebookstore.repository;

import java.util.Optional;

import es.codeurjc.readmebookstore.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String username);

    public Page<User> findAll(Pageable page);

    @Query(value = "SELECT * FROM user WHERE user.id <> :adminid", nativeQuery = true)
    public Page<User> findAll(long adminid, Pageable page);

    @Query(value = "SELECT * FROM user WHERE name = :username", nativeQuery = true)
    Optional<User> findByNameopt(String username);
    
}