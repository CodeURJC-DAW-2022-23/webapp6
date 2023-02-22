package es.codeurjc.readmebookstore.service;

import java.io.IOException;
import java.net.URISyntaxException;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.codeurjc.readmebookstore.model.Book;
import es.codeurjc.readmebookstore.model.User;
import es.codeurjc.readmebookstore.model.Review;
import es.codeurjc.readmebookstore.model.Offers;
import es.codeurjc.readmebookstore.repository.BookRepository;
import es.codeurjc.readmebookstore.repository.UserRepository;
import es.codeurjc.readmebookstore.repository.ReviewRepository;
import es.codeurjc.readmebookstore.repository.OffersRepository;
import jakarta.annotation.PostConstruct;

@Service
public class DatabaseInitializer {

	@Autowired
	private BookRepository bookRepository;

    @Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

    @Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private OffersRepository offerRepository;

	

	@PostConstruct
	public void init() throws IOException, URISyntaxException {

		// Sample books

		Book book1 = new Book("Elantris",
				"Brandon Sanderson", "Fantasía");
		//setBookImage(book1, "/sample_images/tus_zonas_erroneas.jpg");
		bookRepository.save(book1);

		Book book2 = new Book("Nacidos de la bruma",
		"Brandon Sanderson", "Fantasía");
		//setBookImage(book2, "/sample_images/la_vida_secreta_de_la_mente.jpg");
		bookRepository.save(book2);

		Book book3 = new Book("El Archivo de las Tormentas",
		"Brandon Sanderson", "Fantasía");
		//setBookImage(book3, "/sample_images/la_vida_secreta_de_la_mente.jpg");
		bookRepository.save(book3);

		Book book4 = new Book("El Señor de los Anillos",
		"J. R. R. Tolkien", "Fantasía");
		//setBookImage(book4, "/sample_images/la_vida_secreta_de_la_mente.jpg");
		bookRepository.save(book4);

		Book book5 = new Book("Harry Potter",
		"J. K. Rowling", "Fantasía");
		//setBookImage(book5, "/sample_images/la_vida_secreta_de_la_mente.jpg");
		bookRepository.save(book5);

		Book book6 = new Book("La Ilíada",
		"Homero", "Literatura clásica");
		//setBookImage(book6, "/sample_images/la_vida_secreta_de_la_mente.jpg");
		bookRepository.save(book6);

		Book book7 = new Book("La Odisea",
		"Homero", "Literatura clásica");
		//setBookImage(book7, "/sample_images/la_vida_secreta_de_la_mente.jpg");
		bookRepository.save(book7);

		Book book8 = new Book("La Eneida",
		"Virgilio", "Literatura clásica");
		//setBookImage(book8, "/sample_images/la_vida_secreta_de_la_mente.jpg");
		bookRepository.save(book8);

		Book book9 = new Book("La República",
		"Platón", "Literatura clásica");
		//setBookImage(book9, "/sample_images/la_vida_secreta_de_la_mente.jpg");
		bookRepository.save(book9);

		Book book10 = new Book("Arte de amar",
		"Ovidio", "Literatura clásica");
		//setBookImage(book10, "/sample_images/la_vida_secreta_de_la_mente.jpg");
		bookRepository.save(book10);

		Book book11 = new Book("Don Quijote de la Mancha",
		"Miguel de Cervantes", "Literatura universal");
		//setBookImage(book11, "/sample_images/la_vida_secreta_de_la_mente.jpg");
		bookRepository.save(book11);

		Book book12 = new Book("Romeo y Julieta",
		"William Shakespeare", "Literatura universal");
		//setBookImage(book12, "/sample_images/la_vida_secreta_de_la_mente.jpg");
		bookRepository.save(book12);

		Book book13 = new Book("Cumbres borrascosas",
		"Emily Bronte", "Literatura universal");
		//setBookImage(book13, "/sample_images/la_vida_secreta_de_la_mente.jpg");
		bookRepository.save(book13);

		Book book14 = new Book("El retrato de Dorian Gray",
		"Oscar Wilde", "Literatura universal");
		//setBookImage(book14, "/sample_images/la_vida_secreta_de_la_mente.jpg");
		bookRepository.save(book14);

		Book book15 = new Book("El Conde de Montecristo",
		"Alejandro Dumas", "Literatura universal");
		//setBookImage(book15, "/sample_images/la_vida_secreta_de_la_mente.jpg");
		bookRepository.save(book15);

		Book book16 = new Book("El primer hombre de Roma",
		"Colleen McCullough", "Novela histórica");
		//setBookImage(book16, "/sample_images/la_vida_secreta_de_la_mente.jpg");
		bookRepository.save(book16);

		Book book17 = new Book("Trilogía de Trajano",
		"Santiago Posteguillo", "Novela histórica");
		//setBookImage(book17, "/sample_images/la_vida_secreta_de_la_mente.jpg");
		bookRepository.save(book17);

		Book book18 = new Book("Los pilares de la Tierra",
		"Ken Follett", "Novela histórica");
		//setBookImage(book18, "/sample_images/la_vida_secreta_de_la_mente.jpg");
		bookRepository.save(book18);

		Book book19 = new Book("El médico",
		"Noah Gordon", "Novela histórica");
		//setBookImage(book19, "/sample_images/la_vida_secreta_de_la_mente.jpg");
		bookRepository.save(book19);

		Book book20 = new Book("Episodios Nacionales",
		"Benito Pérez Galdos", "Novela histórica");
		//setBookImage(book20, "/sample_images/la_vida_secreta_de_la_mente.jpg");
		bookRepository.save(book20);

		//bookRepository.save(new Book("CASI SIN QUERER", "El amor algunasveces."));

		// Sample users

		userRepository.save(new User("user", passwordEncoder.encode("pass"), "USER", "USER"));
		userRepository.save(new User("admin", passwordEncoder.encode("adminpass"), "USER", "ADMIN"));
	} 

	/*public void setBookImage(Book book, String classpathResource) throws IOException {
		book.setImage(true);
		Resource image = new ClassPathResource(classpathResource);
		book.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
	}*/
}
