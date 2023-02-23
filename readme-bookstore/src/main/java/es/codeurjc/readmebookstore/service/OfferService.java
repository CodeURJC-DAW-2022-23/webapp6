package es.codeurjc.readmebookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import es.codeurjc.readmebookstore.model.Offer;
import es.codeurjc.readmebookstore.repository.OfferRepository;

@Service
public class OfferService {

    @Autowired
	private OfferRepository repository;

	public Optional<Offer> findById(long id) {
		return repository.findById(id);
	}
	
	public boolean exist(long id) {
		return repository.existsById(id);
	}

	public Page<Offer> findAll(int n) {
		return repository.findAll( PageRequest.of(n, 5));
	}

	public List<Offer> findAll() {
		return repository.findAll();
	}

	public void save(Offer offers) {
		repository.save(offers);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}
    
}
