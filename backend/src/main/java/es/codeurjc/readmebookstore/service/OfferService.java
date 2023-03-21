package es.codeurjc.readmebookstore.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
		return repository.findAll( PageRequest.of(n, 4));
	}

	public List<Offer> findAll() {
		return repository.findAll();
	}

	public Page<Offer> findOffersNotSoldByBook(long id, int n) {
		return repository.findByOffersNotSoldByBook(id, PageRequest.of(n, 4));
	}

	public List<Offer> findOffersNotSoldByBook(long id) {
		return repository.findByOffersNotSoldByBook(id);
	}

	public List<Offer> findOffersNotSoldByUser(long id) {
		return repository.findByOffersNotSoldByUser(id);
	}

	public Page<Offer> findOffersNotSoldByUser(long id, int n) {
		return repository.findByOffersNotSoldByUser(id, PageRequest.of(n, 4));
	}

	public Page<Offer> findShoppingHistorial(long id, int n) {
		return repository.findByShoppingHistorial(id, PageRequest.of(n, 4));
	}

	public List<Offer> findShoppingHistorial(long id) {
		return repository.findByShoppingHistorial(id);
	}

	public List<Offer> findBooksBought(long id) {
		return repository.findBooksBought(id);
	}

	public void save(Offer offers) {
		repository.save(offers);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}

	public void updateImage(Offer offer, boolean removeImage, MultipartFile imageField)
            throws IOException, SQLException {
        if (!imageField.isEmpty()) {
            offer.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
            offer.setImage(true);
        } else {
            if (removeImage) {
                offer.setImageFile(null);
                offer.setImage(false);
            } else {
                Offer dbOffer = findById(offer.getId()).orElseThrow();
                if (dbOffer.hasImage()) {
                    offer.setImageFile(BlobProxy.generateProxy(dbOffer.getImageFile().getBinaryStream(),
                            dbOffer.getImageFile().length()));
                    offer.setImage(true);
                }
            }
        }
    }
    
}
