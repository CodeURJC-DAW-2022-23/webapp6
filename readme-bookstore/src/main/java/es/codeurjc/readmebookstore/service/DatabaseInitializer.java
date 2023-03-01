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

		Book book4 = new Book("El Señor de los Anillos", "J. R. R. Tolkien", "Fantasía");
		setBookImage(book4, "/static/img/book-covers/el-señor-de-los-anillos.jpeg");
		bookRepository.save(book4);

		Book book5 = new Book("Harry Potter", "J. K. Rowling", "Fantasía");
		setBookImage(book5, "/static/img/book-covers/harry-potter.jpg");
		bookRepository.save(book5);
		
		Book book6 = new Book("La Ilíada", "Homero", "Literatura clásica");
		setBookImage(book6, "/static/img/book-covers/la-iliada.jpg");
		bookRepository.save(book6);
		
		Book book7 = new Book("La Odisea", "Homero", "Literatura clásica");
		setBookImage(book7, "/static/img/book-covers/la-odisea.png");
		bookRepository.save(book7);
		
		Book book8 = new Book("La Eneida", "Virgilio", "Literatura clásica");
		setBookImage(book8, "/static/img/book-covers/la-eneida.jpg");
		bookRepository.save(book8);
		
		Book book9 = new Book("La República", "Platón", "Literatura clásica");
		setBookImage(book9, "/static/img/book-covers/la-republica.jpg");
		bookRepository.save(book9);
		
		Book book10 = new Book("Arte de amar", "Ovidio", "Literatura clásica");
		setBookImage(book10, "/static/img/book-covers/el-arte-de-amar.jpg");
		bookRepository.save(book10);

		Book book11 = new Book("Don Quijote de la Mancha", "Miguel de Cervantes", "Literatura universal");
		setBookImage(book11, "/static/img/book-covers/don-quijote.png");
		bookRepository.save(book11);
		
		Book book12 = new Book("Romeo y Julieta", "William Shakespeare", "Literatura universal");
		setBookImage(book12, "/static/img/book-covers/romeo-y-julieta.jpg");
		bookRepository.save(book12);
		
		Book book13 = new Book("Cumbres borrascosas", "Emily Bronte", "Literatura universal");
		setBookImage(book13, "/static/img/book-covers/cumbres-borrascosas.jpg");
		bookRepository.save(book13);
		
		Book book14 = new Book("El retrato de Dorian Gray", "Oscar Wilde", "Literatura universal");
		setBookImage(book14, "/static/img/book-covers/el-retrato-de-Dorian-Gray.jpg");
		bookRepository.save(book14);
		
		Book book15 = new Book("El Conde de Montecristo", "Alejandro Dumas", "Literatura universal");
		setBookImage(book15, "/static/img/book-covers/el-conde-de-Montecristo.jpg");
		bookRepository.save(book15);
		
		Book book16 = new Book("El primer hombre de Roma", "Colleen McCullough", "Novela histórica");
		setBookImage(book16, "/static/img/book-covers/el-primer-hombre-de-Roma.jpg");
		bookRepository.save(book16);
		
		Book book17 = new Book("Trilogía de Trajano", "Santiago Posteguillo", "Novela histórica");
		setBookImage(book17, "/static/img/book-covers/trilogia-de-trajano.jpg");
		bookRepository.save(book17);
		
		Book book18 = new Book("Los pilares de la Tierra", "Ken Follett", "Novela histórica");
		setBookImage(book18, "/static/img/book-covers/los-pilares-de-la-tierra.jpg");
		bookRepository.save(book18);
		
		Book book19 = new Book("El médico", "Noah Gordon", "Novela histórica");
		setBookImage(book19, "/static/img/book-covers/el-medico.jpg");
		bookRepository.save(book19);
		
		Book book20 = new Book("Episodios Nacionales", "Benito Pérez Galdos", "Novela histórica");
		setBookImage(book20, "/static/img/book-covers/episodios-nacionales.jpg");
		bookRepository.save(book20);

		// Sample users
		User admin = new User("admin", passwordEncoder.encode("adminpass"), "admin@gmail.com", "ADMIN");
		userRepository.save(admin);
		User user1 = new User("user", passwordEncoder.encode("pass"), "user1@mail.ex", "USER");
		setUserImage(user1, "/static/img/user-profiles/example-profile.png");
		userRepository.save(user1);
		User user2 = new User("User2", passwordEncoder.encode("pass"), "user2@mail.ex", "USER");
		userRepository.save(user2);
		User user3 = new User("jose", passwordEncoder.encode("jose"), "jose@gmail.com", "USER");
		userRepository.save(user3);
		User user4 = new User("maria", passwordEncoder.encode("maria"), "maria@gmail.com", "USER");
		userRepository.save(user4);
		User user5 = new User("carlos", passwordEncoder.encode("carlos"), "carlos@gmail.com", "USER");
		userRepository.save(user5);
		User user6 = new User("carlota", passwordEncoder.encode("carlota"), "carlota@gmail.com", "USER");
		userRepository.save(user6);
		User user7 = new User("sonia", passwordEncoder.encode("sonia"), "sonia@gmail.com", "USER");
		userRepository.save(user7);

		// Sample reviews
		Review review0 = new Review(
				"Novela policiaca contemporanea que mezcla distintos tipos de narración para llevar al lector a la Barcelona de los años 20.",
				new Date(), book0, user3);
		reviewRepository.save(review0);

		Review review1 = new Review("Increible libro", new Date(), book0, user4);
		reviewRepository.save(review1);

		Review review2 = new Review("Me gusta mucho", new Date(), book0, user5);
		reviewRepository.save(review2);

		Review review4 = new Review("Alucinante", new Date(), book1, user3);
		reviewRepository.save(review4);

		Review review5 = new Review("Se me hizo bola", new Date(), book1, user4);
		reviewRepository.save(review5);

		Review review6 = new Review("Muy guay", new Date(), book2, user5);
		reviewRepository.save(review6);

		Review review7 = new Review("No podía dejar de leerlo", new Date(), book2, user4);
		reviewRepository.save(review7);

		Review review8 = new Review("Magnífico libro", new Date(), book3, user3);
		reviewRepository.save(review8);

		Review review9 = new Review("Esperaba más del libro", new Date(), book3, user4);
		reviewRepository.save(review9);

		Review review10 = new Review("Divertido", new Date(), book3, user5);
		reviewRepository.save(review10);

		Review review11 = new Review("Te engancha muchísimo", new Date(), book4, user3);
		reviewRepository.save(review11);

		Review review12 = new Review("No me esperaba el final", new Date(), book5, user4);
		reviewRepository.save(review12);

		Review review13 = new Review("Merece mucho la pena", new Date(), book6, user3);
		reviewRepository.save(review13);

		Review review14 = new Review("Recomendable 100%", new Date(), book6, user5);
		reviewRepository.save(review14);

		Review review15 = new Review("Super divertido", new Date(), book7, user5);
		reviewRepository.save(review15);

		Review review16 = new Review("Recomendado", new Date(), book8, user3);
		reviewRepository.save(review16);

		Review review17 = new Review("Me encanta", new Date(), book8, user4);
		reviewRepository.save(review17);

		Review review18 = new Review("Me lo he leido 5 veces y lo volvería a hacer", new Date(), book9, user4);
		reviewRepository.save(review18);

		Review review19 = new Review("Una pasada de libro", new Date(), book10, user3);
		reviewRepository.save(review19);

		Review review20 = new Review("De los mejores libros que he leído", new Date(), book11, user2);
		reviewRepository.save(review20);

		Review review21 = new Review("Me encanta", new Date(), book12, user3);
		reviewRepository.save(review21);

		Review review22 = new Review("Esperaba más del libro", new Date(), book12, user6);
		reviewRepository.save(review22);

		Review review23 = new Review("Divertido", new Date(), book13, user7);
		reviewRepository.save(review23);

		Review review24 = new Review("Te engancha muchísimo", new Date(), book13, user3);
		reviewRepository.save(review24);

		Review review25 = new Review("No me esperaba el final", new Date(), book14, user4);
		reviewRepository.save(review25);

		Review review26 = new Review("Merece mucho la pena", new Date(), book15, user7);
		reviewRepository.save(review26);

		Review review27 = new Review("Recomendable 100%", new Date(), book15, user6);
		reviewRepository.save(review27);

		Review review28 = new Review("Super divertido", new Date(), book16, user5);
		reviewRepository.save(review28);

		Review review29 = new Review("Recomendado", new Date(), book16, user7);
		reviewRepository.save(review29);

		Review review30 = new Review("Me encanta", new Date(), book17, user6);
		reviewRepository.save(review30);

		Review review31 = new Review("Me lo he leido 5 veces y lo volvería a hacer", new Date(), book18, user7);
		reviewRepository.save(review31);

		Review review32 = new Review("Una pasada de libro", new Date(), book19, user6);
		reviewRepository.save(review32);

		Review review33 = new Review("De los mejores libros que he leído", new Date(), book20, user7);
		reviewRepository.save(review33);

		Review review34 = new Review("Me encanta", new Date(), book20, user6);
		reviewRepository.save(review34);

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
