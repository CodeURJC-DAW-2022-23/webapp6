package es.codeurjc.readmebookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import es.codeurjc.readmebookstore.model.Review;
import es.codeurjc.readmebookstore.repository.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repository;

	public Optional<Review> findById(long id) {
		return repository.findById(id);
	}

	public List<Review> findById(List<Long> ids){
		return repository.findAllById(ids);
	}
	
	public boolean exist(long id) {
		return repository.existsById(id);
	}

	public Page<Review> findAll(int n) {
		return repository.findAll( PageRequest.of(n, 4));
	}

	public List<Review> findAll() {
		return repository.findAll();
	}

	public void save(Review review) {
		repository.save(review);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}

	public List<Review> findAllReviewsByBook(Long bookid) {
		return repository.findByAllReviewsByBook(bookid);
	}

	public Page<Review> findAllReviewsByBook(Long bookid, int n) {
		return repository.findByAllReviewsByBook(bookid, PageRequest.of(n, 4));
	}

	public Page<Review> findAllReviewsByUser(Long userid, int n) {
		return repository.findByAllReviewsByUser(userid, PageRequest.of(n, 4));
	}

	public List<Review> findAllReviewsByUser(Long userid) {
		return repository.findByAllReviewsByUser(userid);
	}
	
}
