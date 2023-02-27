package es.codeurjc.readmebookstore.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.codeurjc.readmebookstore.model.Book;
import es.codeurjc.readmebookstore.model.Offer;
import es.codeurjc.readmebookstore.model.User;
import es.codeurjc.readmebookstore.model.Review;
import es.codeurjc.readmebookstore.repository.BookRepository;
import es.codeurjc.readmebookstore.repository.UserRepository;
import es.codeurjc.readmebookstore.repository.ReviewRepository;
import es.codeurjc.readmebookstore.repository.OfferRepository;
import javax.annotation.PostConstruct;

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
	private OfferRepository offerRepository;

	@PostConstruct
	public void init() throws IOException, URISyntaxException {

		// Sample books

		Book book0 = new Book("La verdad sobre el caso Savolta", "Eduardo Mendoza", "Policiaca");
		setBookImage(book0, "/static/img/book-covers/la-verdad-sobre-el-caso-savolta.png");
		bookRepository.save(book0);

		Book book1 = new Book("Elantris", "Brandon Sanderson", "Fantasia");
		setBookImage(book1, "/static/img/book-covers/elantris.jpg");
		bookRepository.save(book1);

		Book book2 = new Book("Nacidos de la bruma", "Brandon Sanderson", "Fantasia");
		setBookImage(book2, "/static/img/book-covers/nacidos-de-la-bruma.jpg");
		bookRepository.save(book2);

		Book book3 = new Book("El Archivo de las Tormentas", "Brandon Sanderson", "Fantasia");
		setBookImage(book3, "/static/img/book-covers/el-archivo-de-las-tormentas.jpg");
		bookRepository.save(book3);

		// Sample users
		User admin = new User("admin", passwordEncoder.encode("adminpass"), "admin@gmail.com", "ADMIN");
		userRepository.save(admin);
		User user1 = new User("user", passwordEncoder.encode("pass"), "user1@mail.ex", "USER");
		setUserImage(user1, "/static/img/user-profiles/example-profile.png");
		userRepository.save(user1);
		User user2 = new User("User2", passwordEncoder.encode("pass"), "user2@mail.ex", "USER");
		userRepository.save(user2);

		// Sample reviews
		Review testReview = new Review(
				"Novela policiaca contemporanea que mezcla distintos tipos de narración para llevar al lector a la Barcelona de los años 20.",
				new Date(), book0, user1);
		reviewRepository.save(testReview);

		// Sample offers
		Offer testOffer = new Offer(new Date(), "Seix Barral", "This is a test description", 10f, book0, user1);
		setOfferImage(testOffer, "/static/img/book-offers/example-book.jpg");
		offerRepository.save(testOffer);
	}

	public void setBookImage(Book book, String classpathResource) throws IOException {
		book.setImage(true);
		Resource image = new ClassPathResource(classpathResource);
		book.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
	}

	public void setUserImage(User user, String classpathResource) throws IOException {
		user.setImage(true);
		Resource image = new ClassPathResource(classpathResource);
		user.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
	}

	public void setOfferImage(Offer offer, String classpathResource) throws IOException {
		offer.setImage(true);
		Resource image = new ClassPathResource(classpathResource);
		offer.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
	}

	// More books down here:

	// Book book4 = new Book("El Señor de los Anillos",
	// "J. R. R. Tolkien", "Fantasía");
	// // setBookImage(book4, "/sample_images/la_vida_secreta_de_la_mente.jpg");
	// bookRepository.save(book4);
	//
	// Book book5 = new Book("Harry Potter",
	// "J. K. Rowling", "Fantasía");
	// // setBookImage(book5, "/sample_images/la_vida_secreta_de_la_mente.jpg");
	// bookRepository.save(book5);
	//
	// Book book6 = new Book("La Ilíada",
	// "Homero", "Literatura clásica");
	// // setBookImage(book6, "/sample_images/la_vida_secreta_de_la_mente.jpg");
	// bookRepository.save(book6);
	//
	// Book book7 = new Book("La Odisea",
	// "Homero", "Literatura clásica");
	// // setBookImage(book7, "/sample_images/la_vida_secreta_de_la_mente.jpg");
	// bookRepository.save(book7);
	//
	// Book book8 = new Book("La Eneida",
	// "Virgilio", "Literatura clásica");
	// // setBookImage(book8, "/sample_images/la_vida_secreta_de_la_mente.jpg");
	// bookRepository.save(book8);
	//
	// Book book9 = new Book("La República",
	// "Platón", "Literatura clásica");
	// // setBookImage(book9, "/sample_images/la_vida_secreta_de_la_mente.jpg");
	// bookRepository.save(book9);
	//
	// Book book10 = new Book("Arte de amar",
	// "Ovidio", "Literatura clásica");
	// // setBookImage(book10, "/sample_images/la_vida_secreta_de_la_mente.jpg");
	// bookRepository.save(book10);
	//
	// Book book11 = new Book("Don Quijote de la Mancha",
	// "Miguel de Cervantes", "Literatura universal");
	// // setBookImage(book11, "/sample_images/la_vida_secreta_de_la_mente.jpg");
	// bookRepository.save(book11);
	//
	// Book book12 = new Book("Romeo y Julieta",
	// "William Shakespeare", "Literatura universal");
	// // setBookImage(book12, "/sample_images/la_vida_secreta_de_la_mente.jpg");
	// bookRepository.save(book12);
	//
	// Book book13 = new Book("Cumbres borrascosas",
	// "Emily Bronte", "Literatura universal");
	// // setBookImage(book13, "/sample_images/la_vida_secreta_de_la_mente.jpg");
	// bookRepository.save(book13);
	//
	// Book book14 = new Book("El retrato de Dorian Gray",
	// "Oscar Wilde", "Literatura universal");
	// // setBookImage(book14, "/sample_images/la_vida_secreta_de_la_mente.jpg");
	// bookRepository.save(book14);
	//
	// Book book15 = new Book("El Conde de Montecristo",
	// "Alejandro Dumas", "Literatura universal");
	// // setBookImage(book15, "/sample_images/la_vida_secreta_de_la_mente.jpg");
	// bookRepository.save(book15);
	//
	// Book book16 = new Book("El primer hombre de Roma",
	// "Colleen McCullough", "Novela histórica");
	// // setBookImage(book16, "/sample_images/la_vida_secreta_de_la_mente.jpg");
	// bookRepository.save(book16);
	//
	// Book book17 = new Book("Trilogía de Trajano",
	// "Santiago Posteguillo", "Novela histórica");
	// // setBookImage(book17, "/sample_images/la_vida_secreta_de_la_mente.jpg");
	// bookRepository.save(book17);
	//
	// Book book18 = new Book("Los pilares de la Tierra",
	// "Ken Follett", "Novela histórica");
	// // setBookImage(book18, "/sample_images/la_vida_secreta_de_la_mente.jpg");
	// bookRepository.save(book18);
	//
	// Book book19 = new Book("El médico",
	// "Noah Gordon", "Novela histórica");
	// // setBookImage(book19, "/sample_images/la_vida_secreta_de_la_mente.jpg");
	// bookRepository.save(book19);
	//
	// Book book20 = new Book("Episodios Nacionales",
	// "Benito Pérez Galdos", "Novela histórica");
	// // setBookImage(book20, "/sample_images/la_vida_secreta_de_la_mente.jpg");
	// bookRepository.save(book20);
	//
	// // bookRepository.save(new Book("CASI SIN QUERER", "El amor algunasveces."));
}
