package es.codeurjc.readmebookstore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import es.codeurjc.readmebookstore.model.Offers;
import es.codeurjc.readmebookstore.repository.OffersRepository;

public class OffersService {

    @Autowired
	private OffersRepository repository;

	public Optional<Offers> findById(long id) {
		return repository.findById(id);
	}
	
	public boolean exist(long id) {
		return repository.existsById(id);
	}

	public Page<Offers> findAll(int n) {
		return repository.findAll( PageRequest.of(n, 5));
	}

	public void save(Offers offers) {
		repository.save(offers);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}
    
}
