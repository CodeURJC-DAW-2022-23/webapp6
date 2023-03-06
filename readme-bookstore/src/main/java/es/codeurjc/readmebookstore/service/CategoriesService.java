package es.codeurjc.readmebookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.readmebookstore.model.Categories;
import es.codeurjc.readmebookstore.repository.CategoriesRepository;

@Service
public class CategoriesService {

	@Autowired
	private CategoriesRepository repository;

	public Optional<Categories> findById(long id) {
		return repository.findById(id);
	}

	public boolean exist(long id) {
		return repository.existsById(id);
	}

	public List<Categories> findAll() {
		return repository.findAll();
	}

	public void save(Categories categories) {
		repository.save(categories);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}

    public Categories findByName(String name) {
		return repository.findByName(name);
	}

}
