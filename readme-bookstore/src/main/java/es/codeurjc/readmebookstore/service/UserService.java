package es.codeurjc.readmebookstore.service;

import es.codeurjc.readmebookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.Optional;
import java.util.List;
import es.codeurjc.readmebookstore.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }


    public User findByName(String name) {
        return userRepository.findByName(name).orElseThrow();

    }

    public boolean exist(long id) {
        return userRepository.existsById(id);
    }

    public Page<User> findAll(int n) {
        return userRepository.findAll( PageRequest.of(n, 5));
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


}