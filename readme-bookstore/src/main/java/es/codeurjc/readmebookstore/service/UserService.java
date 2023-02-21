package es.codeurjc.readmebookstore.service;

import es.codeurjc.readmebookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.Optional;
import es.codeurjc.readmebookstore.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserRepository repository;

    public User findById(long id) {
        return repository.findById(id).orElseThrow();

    }

    public User findByName(String name) {
        return repository.findByName(name).orElseThrow();

    }

    public boolean exist(long id) {
        return repository.existsById(id);
    }

    public Page<User> findAll(int n) {
        return repository.findAll( PageRequest.of(n, 5));
    }

    public void save(User user) {
        repository.save(user);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }



}