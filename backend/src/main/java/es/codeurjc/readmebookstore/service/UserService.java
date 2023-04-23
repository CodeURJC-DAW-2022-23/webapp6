package es.codeurjc.readmebookstore.service;

import es.codeurjc.readmebookstore.repository.UserRepository;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.Optional;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import es.codeurjc.readmebookstore.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    public Optional<User> findByNameopt(String name) {
        return userRepository.findByNameopt(name);
    }

    public boolean exist(long id) {
        return userRepository.existsById(id);
    }

    public Page<User> findAll(Long id, int n) {
        return userRepository.findAll(id, PageRequest.of(n, 4));
    }

    public void save(User user) {
        userRepository.saveAndFlush(user);
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Page<User> findAll(int n) {
		return userRepository.findAll( PageRequest.of(n, 4));
	}

    public void updateImage(User user, boolean removeImage, MultipartFile imageField)
            throws IOException, SQLException {
        if (!imageField.isEmpty()) {
            user.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
            user.setImage(true);
        } else {
            if (removeImage) {
                user.setImageFile(null);
                user.setImage(false);
            } else {
                User dbUser = findById(user.getId()).orElseThrow();
                if (dbUser.hasImage()) {
                    user.setImageFile(BlobProxy.generateProxy(dbUser.getImageFile().getBinaryStream(),
                            dbUser.getImageFile().length()));
                    user.setImage(true);
                }
            }
        }
    }

}