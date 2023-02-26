package es.codeurjc.readmebookstore.controller;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import jakarta.servlet.http.HttpServletRequest;
import es.codeurjc.readmebookstore.model.Book;
import es.codeurjc.readmebookstore.model.Offer;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import es.codeurjc.readmebookstore.service.BookService;
import es.codeurjc.readmebookstore.service.UserService;
import es.codeurjc.readmebookstore.service.OfferService;
import es.codeurjc.readmebookstore.repository.UserRepository;
import es.codeurjc.readmebookstore.repository.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class BookController {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

	@Autowired
    private OfferService offerService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;


	@ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();
		
        if (principal != null) {
            model.addAttribute("logged", true);
        } else {
            model.addAttribute("logged", false);
        }
    }

 
	@GetMapping("/books")
	public String showBooks(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
		return "books-general-page";
	}

    @GetMapping("/book/{id}")
	public String showBook(Model model, @PathVariable long id) {
        Optional<Book> book = bookRepository.findById(id);
		List<Offer> offers = offerService.findOffersNotSoldByBook(id);
        model.addAttribute("book", book.get());
        model.addAttribute("reviews", book.get().getReviews());
        model.addAttribute("offers", offers);
		return "book-particular-page";
	}

	@GetMapping("/books/{id}/image")
	public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {

		Optional<Book> game = bookService.findById(id);
		if (game.isPresent() && game.get().getImageFile() != null) {

			Resource file = new InputStreamResource(game.get().getImageFile().getBinaryStream());

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
					.contentLength(game.get().getImageFile().length()).body(file);

		} else {
			return ResponseEntity.notFound().build();
		}
	}


	private void updateImage(Book book, boolean removeImage, MultipartFile imageField) throws IOException, SQLException {
		
		if (!imageField.isEmpty()) {
			book.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			book.setImage(true);
		} else {
			if (removeImage) {
				book.setImageFile(null);
				book.setImage(false);
			} else {
				// Maintain the same image loading it before updating the book
				Book dbGame = bookService.findById(book.getId()).orElseThrow();
				if (dbGame.getImage()) {
					book.setImageFile(BlobProxy.generateProxy(dbGame.getImageFile().getBinaryStream(),
							dbGame.getImageFile().length()));
					book.setImage(true);
				}
			}
		}
	}


}