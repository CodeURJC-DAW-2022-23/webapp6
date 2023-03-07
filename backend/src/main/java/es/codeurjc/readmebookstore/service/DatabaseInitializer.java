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
import es.codeurjc.readmebookstore.model.Categories;
import es.codeurjc.readmebookstore.model.Offer;
import es.codeurjc.readmebookstore.model.User;
import es.codeurjc.readmebookstore.model.Review;
import es.codeurjc.readmebookstore.repository.BookRepository;
import es.codeurjc.readmebookstore.repository.CategoriesRepository;
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

	@Autowired
	private CategoriesRepository categoriesRepository;

	@PostConstruct
	public void init() throws IOException, URISyntaxException {

		//Categories

		Categories categories0 = new Categories("Policiaca");
		categoriesRepository.save(categories0);

		Categories categories1 = new Categories("Misterio");
		categoriesRepository.save(categories1);

		Categories categories2 = new Categories("Drama");
		categoriesRepository.save(categories2);

		Categories categories3 = new Categories("Novela");
		categoriesRepository.save(categories3);

		Categories categories4 = new Categories("Fantasia");
		categoriesRepository.save(categories4);

		Categories categories5 = new Categories("Alta Fantasia");
		categoriesRepository.save(categories5);

		Categories categories6 = new Categories("Fantasia Epica");
		categoriesRepository.save(categories6);

		Categories categories7 = new Categories("Aventuras");
		categoriesRepository.save(categories7);

		Categories categories8 = new Categories("Juvenil");
		categoriesRepository.save(categories8);

		Categories categories9 = new Categories("Literatura clásica");
		categoriesRepository.save(categories9);

		Categories categories10 = new Categories("Epopeya");
		categoriesRepository.save(categories10);

		Categories categories11 = new Categories("Poesia");
		categoriesRepository.save(categories11);

		Categories categories12 = new Categories("Politica");
		categoriesRepository.save(categories12);

		Categories categories13 = new Categories("Filosofia");
		categoriesRepository.save(categories13);

		Categories categories14 = new Categories("Elegia");
		categoriesRepository.save(categories14);

		Categories categories15 = new Categories("Literatura universal");
		categoriesRepository.save(categories15);

		Categories categories16 = new Categories("Satira");
		categoriesRepository.save(categories16);

		Categories categories17 = new Categories("Comedia");
		categoriesRepository.save(categories17);

		Categories categories18 = new Categories("Tragedia");
		categoriesRepository.save(categories18);

		Categories categories19 = new Categories("Romance");
		categoriesRepository.save(categories19);

		Categories categories20 = new Categories("Novela histórica");
		categoriesRepository.save(categories20);

		Categories categories21 = new Categories("Ficcion");
		categoriesRepository.save(categories21);

		// Sample books

		Book book0 = new Book("La verdad sobre el caso Savolta", "Eduardo Mendoza", "Policiaca");
		setBookImage(book0, "/static/img/book-covers/la-verdad-sobre-el-caso-savolta.png");
		book0.setCategories(categories0);
		book0.setCategories(categories1);
		book0.setCategories(categories2);
		book0.setCategories(categories3);
		bookRepository.save(book0);

		Book book1 = new Book("Elantris", "Brandon Sanderson", "Fantasia");
		setBookImage(book1, "/static/img/book-covers/elantris.jpg");
		book1.setCategories(categories3);
		book1.setCategories(categories4);
		book1.setCategories(categories5);
		bookRepository.save(book1);

		Book book2 = new Book("Nacidos de la bruma", "Brandon Sanderson", "Fantasia");
		setBookImage(book2, "/static/img/book-covers/nacidos-de-la-bruma.jpg");
		book2.setCategories(categories3);
		book2.setCategories(categories4);
		bookRepository.save(book2);

		Book book3 = new Book("El Archivo de las Tormentas", "Brandon Sanderson", "Fantasia");
		setBookImage(book3, "/static/img/book-covers/el-archivo-de-las-tormentas.jpg");
		book3.setCategories(categories3);
		book3.setCategories(categories4);
		book3.setCategories(categories6);
		bookRepository.save(book3);

		Book book4 = new Book("El Señor de los Anillos", "J. R. R. Tolkien", "Fantasía");
		setBookImage(book4, "/static/img/book-covers/el-señor-de-los-anillos.jpeg");
		book4.setCategories(categories3);
		book4.setCategories(categories4);
		book4.setCategories(categories5);
		book4.setCategories(categories7);
		bookRepository.save(book4);

		Book book5 = new Book("Harry Potter", "J. K. Rowling", "Fantasía");
		setBookImage(book5, "/static/img/book-covers/harry-potter.jpg");
		book5.setCategories(categories3);
		book5.setCategories(categories4);
		book5.setCategories(categories7);
		book5.setCategories(categories8);
		bookRepository.save(book5);
		
		Book book6 = new Book("La Ilíada", "Homero", "Literatura clásica");
		setBookImage(book6, "/static/img/book-covers/la-iliada.jpg");
		book6.setCategories(categories9);
		book6.setCategories(categories10);
		book6.setCategories(categories11);
		bookRepository.save(book6);
		
		Book book7 = new Book("La Odisea", "Homero", "Literatura clásica");
		setBookImage(book7, "/static/img/book-covers/la-odisea.png");
		book7.setCategories(categories9);
		book7.setCategories(categories10);
		bookRepository.save(book7);
		
		Book book8 = new Book("La Eneida", "Virgilio", "Literatura clásica");
		setBookImage(book8, "/static/img/book-covers/la-eneida.jpg");
		book8.setCategories(categories4);
		book8.setCategories(categories9);
		book8.setCategories(categories10);
		bookRepository.save(book8);
		
		Book book9 = new Book("La República", "Platón", "Literatura clásica");
		setBookImage(book9, "/static/img/book-covers/la-republica.jpg");
		book9.setCategories(categories9);
		book9.setCategories(categories12);
		book9.setCategories(categories13);
		bookRepository.save(book9);
		
		Book book10 = new Book("Arte de amar", "Ovidio", "Literatura clásica");
		setBookImage(book10, "/static/img/book-covers/el-arte-de-amar.jpg");
		book10.setCategories(categories9);
		book10.setCategories(categories11);
		book10.setCategories(categories14);
		bookRepository.save(book10);

		Book book11 = new Book("Don Quijote de la Mancha", "Miguel de Cervantes", "Literatura universal");
		setBookImage(book11, "/static/img/book-covers/don-quijote.png");
		book11.setCategories(categories3);
		book11.setCategories(categories15);
		book11.setCategories(categories16);
		bookRepository.save(book11);
		
		Book book12 = new Book("Romeo y Julieta", "William Shakespeare", "Literatura universal");
		setBookImage(book12, "/static/img/book-covers/romeo-y-julieta.jpg");
		book12.setCategories(categories2);
		book12.setCategories(categories14);
		book12.setCategories(categories15);
		book12.setCategories(categories17);
		book12.setCategories(categories18);
		book12.setCategories(categories19);
		bookRepository.save(book12);
		
		Book book13 = new Book("Cumbres borrascosas", "Emily Bronte", "Literatura universal");
		setBookImage(book13, "/static/img/book-covers/cumbres-borrascosas.jpg");
		book13.setCategories(categories3);
		book13.setCategories(categories15);
		book13.setCategories(categories18);
		bookRepository.save(book13);
		
		Book book14 = new Book("El retrato de Dorian Gray", "Oscar Wilde", "Literatura universal");
		setBookImage(book14, "/static/img/book-covers/el-retrato-de-Dorian-Gray.jpg");
		book14.setCategories(categories1);
		book14.setCategories(categories7);
		book14.setCategories(categories13);
		book14.setCategories(categories15);
		bookRepository.save(book14);
		
		Book book15 = new Book("El Conde de Montecristo", "Alejandro Dumas", "Literatura universal");
		setBookImage(book15, "/static/img/book-covers/el-conde-de-Montecristo.jpg");
		book15.setCategories(categories3);
		book15.setCategories(categories7);
		book15.setCategories(categories15);
		bookRepository.save(book15);
		
		Book book16 = new Book("El primer hombre de Roma", "Colleen McCullough", "Novela histórica");
		setBookImage(book16, "/static/img/book-covers/el-primer-hombre-de-Roma.jpg");
		book16.setCategories(categories3);
		book16.setCategories(categories20);
		book16.setCategories(categories21);
		bookRepository.save(book16);
		
		Book book17 = new Book("Trilogía de Trajano", "Santiago Posteguillo", "Novela histórica");
		setBookImage(book17, "/static/img/book-covers/trilogia-de-trajano.jpg");
		book17.setCategories(categories3);
		book17.setCategories(categories20);
		book17.setCategories(categories21);
		bookRepository.save(book17);
		
		Book book18 = new Book("Los pilares de la Tierra", "Ken Follett", "Novela histórica");
		setBookImage(book18, "/static/img/book-covers/los-pilares-de-la-tierra.jpg");
		book18.setCategories(categories3);
		book18.setCategories(categories20);
		book18.setCategories(categories21);
		bookRepository.save(book18);
		
		Book book19 = new Book("El médico", "Noah Gordon", "Novela histórica");
		setBookImage(book19, "/static/img/book-covers/el-medico.jpg");
		book19.setCategories(categories7);
		book19.setCategories(categories20);
		book19.setCategories(categories21);
		bookRepository.save(book19);
		
		Book book20 = new Book("Episodios Nacionales", "Benito Pérez Galdos", "Novela histórica");
		setBookImage(book20, "/static/img/book-covers/episodios-nacionales.jpg");
		book20.setCategories(categories3);
		book20.setCategories(categories20);
		book20.setCategories(categories21);
		bookRepository.save(book20);

		// Sample users
		User admin = new User("admin", passwordEncoder.encode("adminpass"), "admin@gmail.com", "ADMIN");
		userRepository.save(admin);

		User user1 = new User("user", passwordEncoder.encode("pass"), "user1@mail.ex", "USER");
		setUserImage(user1, "/static/img/user-profiles/example-profile.png");
		userRepository.save(user1);

		User user2 = new User("User2", passwordEncoder.encode("pass"), "user2@mail.ex", "USER");
		setUserImage(user2, "/static/img/user-profiles/example-profile.png");
		userRepository.save(user2);

		User user3 = new User("jose", passwordEncoder.encode("jose"), "jose@gmail.com", "USER");
		setUserImage(user3, "/static/img/user-profiles/example-profile.png");
		user3.setFavouriteBooks(book8);
		user3.setFavouriteBooks(book13);
		user3.setFavouriteBooks(book15);
		user3.setFavouriteBooks(book16);
		user3.setFavouriteBooks(book19);
		userRepository.save(user3);

		User user4 = new User("maria", passwordEncoder.encode("maria"), "maria@gmail.com", "USER");
		setUserImage(user4, "/static/img/user-profiles/example-profile.png");
		userRepository.save(user4);

		User user5 = new User("carlos", passwordEncoder.encode("carlos"), "carlos@gmail.com", "USER");
		setUserImage(user5, "/static/img/user-profiles/example-profile.png");
		userRepository.save(user5);

		User user6 = new User("carlota", passwordEncoder.encode("carlota"), "carlota@gmail.com", "USER");
		setUserImage(user6, "/static/img/user-profiles/example-profile.png");
		userRepository.save(user6);

		User user7 = new User("sonia", passwordEncoder.encode("sonia"), "sonia@gmail.com", "USER");
		setUserImage(user7, "/static/img/user-profiles/example-profile.png");
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
		Offer offer1 = new Offer(new Date(), "Seix Barral", "This is a test description", 10f, book0, user3, false, null);
		setOfferImage(offer1, "/static/img/book-offers/savolta-1.jpg");
		offerRepository.save(offer1);

		Offer offer2 = new Offer(new Date(), "TOR BOOKS", "This is a test description", 12f, book0, user4, false, null);
		setOfferImage(offer2, "/static/img/book-offers/savolta-2.jpg");
		offerRepository.save(offer2);

		Offer offer3 = new Offer(new Date(), "TOR BOOKS", "This is a test description", 10f, book1, user5, false, null);
		setOfferImage(offer3, "/static/img/book-offers/elantris-1.jpg");
		offerRepository.save(offer3);

		Offer offer4 = new Offer(new Date(), "SM", "This is a test description", 9f, book1, user6, false, null);
		setOfferImage(offer4, "/static/img/book-offers/elantris-2.jpg");
		offerRepository.save(offer4);

		Offer offer5 = new Offer(new Date(), "Nova", "This is a test description", 15f, book2, user4, false, null);
		setOfferImage(offer5, "/static/img/book-offers/nacidos-de-la-bruma-1.jpg");
		offerRepository.save(offer5);

		Offer offer6 = new Offer(new Date(), "Nova", "This is a test description", 13f, book2, user3, false, null);
		setOfferImage(offer6, "/static/img/book-offers/nacidos-de-la-bruma-2.jpeg");
		offerRepository.save(offer6);

		Offer offer7 = new Offer(new Date(), "Nova", "This is a test description", 15f, book3, user7, false, null);
		setOfferImage(offer7, "/static/img/book-offers/el-archivo-de-las-tormentas-1.jpg");
		offerRepository.save(offer7);

		Offer offer8 = new Offer(new Date(), "Nova", "This is a test description", 13f, book3, user3, false, null);
		setOfferImage(offer8, "/static/img/book-offers/el-archivo-de-las-tormentas-2.jpg");
		offerRepository.save(offer8);	

		Offer offer9 = new Offer(new Date(), "Minotauro", "This is a test description", 10f, book4, user2, false, null);
		setOfferImage(offer9, "/static/img/book-offers/el-señor-de-los-anillos-1.jpg");
		offerRepository.save(offer9);

		Offer offer10 = new Offer(new Date(), "Minotauro", "This is a test description", 17f, book4, user3, false, null);
		setOfferImage(offer10, "/static/img/book-offers/el-señor-de-los-anillos-2.jpg");
		offerRepository.save(offer10);	

		Offer offer11 = new Offer(new Date(), "Minotauro", "This is a test description", 13f, book4, user4, false, null);
		setOfferImage(offer11, "/static/img/book-offers/el-señor-de-los-anillos-3.jpg");
		offerRepository.save(offer11);	

		Offer offer12 = new Offer(new Date(), "Minotauro", "This is a test description", 12f, book4, user5, false, null);
		setOfferImage(offer12, "/static/img/book-offers/el-señor-de-los-anillos-4.jpg");
		offerRepository.save(offer12);	

		Offer offer13 = new Offer(new Date(), "Minotauro", "This is a test description", 11f, book4, user6, false, null);
		setOfferImage(offer13, "/static/img/book-offers/el-señor-de-los-anillos-5.jpg");
		offerRepository.save(offer13);	

		Offer offer14 = new Offer(new Date(), "Minotauro", "This is a test description", 18f, book4, user7, false, null);
		setOfferImage(offer14, "/static/img/book-offers/el-señor-de-los-anillos-6.jpg");
		offerRepository.save(offer14);	

		Offer offer15 = new Offer(new Date(), "Minotauro", "This is a test description", 10f, book11, user2, false, null);
		setOfferImage(offer15, "/static/img/book-offers/don-quijote-1.jpg");
		offerRepository.save(offer15);

		Offer offer16 = new Offer(new Date(), "Minotauro", "This is a test description", 12f, book11, user3, false, null);
		setOfferImage(offer16, "/static/img/book-offers/don-quijote-2.jpg");
		offerRepository.save(offer16);	

		Offer offer17 = new Offer(new Date(), "Minotauro", "This is a test description", 11f, book11, user4, false, null);
		setOfferImage(offer17, "/static/img/book-offers/don-quijote-3.jpg");
		offerRepository.save(offer17);	

		Offer offer18 = new Offer(new Date(), "Minotauro", "This is a test description", 13f, book11, user5, false, null);
		setOfferImage(offer18, "/static/img/book-offers/don-quijote-4.jpg");
		offerRepository.save(offer18);	

		Offer offer19 = new Offer(new Date(), "Minotauro", "This is a test description", 15f, book11, user6, false, null);
		setOfferImage(offer19, "/static/img/book-offers/don-quijote-5.jpg");
		offerRepository.save(offer19);	

		Offer offer20 = new Offer(new Date(), "Minotauro", "This is a test description", 16f, book11, user7, false, null);
		setOfferImage(offer20, "/static/img/book-offers/don-quijote-6.jpeg");
		offerRepository.save(offer20);

		Offer offer21 = new Offer(new Date(), "Minotauro", "This is a test description", 16f, book11, user4, false, null);
		setOfferImage(offer21, "/static/img/book-offers/don-quijote-7.jpg");
		offerRepository.save(offer21);

		Offer offer22 = new Offer(new Date(), "Minotauro", "This is a test description", 16f, book11, user6, false, null);
		setOfferImage(offer22, "/static/img/book-offers/don-quijote-8.jpg");
		offerRepository.save(offer22);

		// Sample offers sold
		Offer offer1s = new Offer(new Date(), "Seix Barral", "This is a test description", 10f, book0, user2, true, user3);
		setOfferImage(offer1s, "/static/img/book-offers/savolta-1.jpg");
		offerRepository.save(offer1s);

		Offer offer3s = new Offer(new Date(), "TOR BOOKS", "This is a test description", 10f, book1, user5, true, user3);
		setOfferImage(offer3s, "/static/img/book-offers/elantris-1.jpg");
		offerRepository.save(offer3s);

		Offer offer5s = new Offer(new Date(), "Nova", "This is a test description", 15f, book2, user4, true, user3);
		setOfferImage(offer5s, "/static/img/book-offers/nacidos-de-la-bruma-1.jpg");
		offerRepository.save(offer5s);
		
		Offer offer7s = new Offer(new Date(), "Nova", "This is a test description", 15f, book3, user7, true, user3);
		setOfferImage(offer7s, "/static/img/book-offers/el-archivo-de-las-tormentas-1.jpg");
		offerRepository.save(offer7s);

		Offer offer9s = new Offer(new Date(), "Minotauro", "This is a test description", 10f, book4, user2, true, user3);
		setOfferImage(offer9s, "/static/img/book-offers/el-señor-de-los-anillos-1.jpg");
		offerRepository.save(offer9s);

		Offer offer16s = new Offer(new Date(), "Minotauro", "This is a test description", 12f, book11, user3, true, user3);
		setOfferImage(offer16s, "/static/img/book-offers/don-quijote-2.jpg");
		offerRepository.save(offer16s);

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

	
}
