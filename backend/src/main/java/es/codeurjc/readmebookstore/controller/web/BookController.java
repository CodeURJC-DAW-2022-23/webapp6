package es.codeurjc.readmebookstore.controller.web;

import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import es.codeurjc.readmebookstore.model.Book;
import es.codeurjc.readmebookstore.model.Offer;
import es.codeurjc.readmebookstore.model.Review;
import es.codeurjc.readmebookstore.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import es.codeurjc.readmebookstore.service.BookService;
import es.codeurjc.readmebookstore.service.ReviewService;
import es.codeurjc.readmebookstore.service.UserService;
import es.codeurjc.readmebookstore.service.OfferService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController extends AlgorithmController {

	@Autowired
	private UserService userService;

	@Autowired
	private BookService bookService;

	@Autowired
	private OfferService offerService;

	@Autowired
	private ReviewService reviewService;


	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if (principal != null) {
			model.addAttribute("logged", true);
		} else {
			model.addAttribute("logged", false);
		}
	}

	/**
	 * @return List with the recomended books using the recomendation algorithm.
	 * @throws Exception
	 */
	private List<Book> getRecommendedBooks(Model model, HttpServletRequest request) throws Exception {
		List<Long> recommendedBooksIds = recommendationAlgorithm (model, request);
		List<Book> recommendedBooks = new ArrayList<Book>();

		for (int i = 0; i < recommendedBooksIds.size(); i++) {
			Book book = bookService.findById(recommendedBooksIds.get(i)).get();
			recommendedBooks.add(book);
		}
		return recommendedBooks;
	}

	@GetMapping("/")
	public String mainPage(Model model, HttpServletRequest request) throws Exception {
		List<Book> recommendedBooks = getRecommendedBooks(model, request);

		model.addAttribute("bestPick", recommendedBooks.get(0));
		model.addAttribute("recommendedBooks", recommendedBooks);

		return "index";
	}

	@GetMapping("/books")
	public String showBooks(Model model, HttpServletRequest request, @RequestParam(defaultValue = "0") int currentPage, @RequestParam(required = false) String searchtext) throws Exception {
		model.addAttribute("bestPick", getRecommendedBooks(model, request).get(0));

		if (searchtext != null) {
			Boolean result = false;
			result = bookService.doSearchTitle(searchtext);
			if (result) {
				return showBook(model, bookService.findByTitle(searchtext).get().getId(), request, 0, 0);
			} else {
				result = bookService.doSearchAuthor(searchtext);
				if (result) {
					model.addAttribute("books", bookService.findPageAuthor(searchtext, currentPage));
				} else {
					result = bookService.doSearchGenre(searchtext);
					if (result) {
						model.addAttribute("books", bookService.findPageGenre(searchtext, currentPage));
					} else {
						result = bookService.doSearchPartial(searchtext);
						if (result) {
							model.addAttribute("books", bookService.findPagePartial(searchtext, currentPage));
						} else {
							return "error-page";
						}
					}
				}
			}
		}
		else {
			model.addAttribute("books", bookService.findAll(currentPage));
		}

		return "books-general-page";
	}

	@GetMapping("/books/{id}")

	public String showBook(Model model, @PathVariable long id, HttpServletRequest request, @RequestParam(defaultValue = "0") int currentReviewsPage,  @RequestParam(defaultValue = "0") int currentOffersPage) {
		Book book = bookService.findById(id).get();
		Page<Offer> offers = offerService.findOffersNotSoldByBook(id, currentOffersPage);
		Page<Review> reviews = reviewService.findAllReviewsByBook(id, currentReviewsPage);
		model.addAttribute("book", book);
		model.addAttribute("reviews", reviews);
		model.addAttribute("offers", offers);
		try {
			Principal principal = request.getUserPrincipal();
			if (principal != null) {
				User usergetid = userService.findByName(principal.getName());
				List<Book> favorites = bookService.isFavorite(usergetid.getId(), id);
				if (favorites.isEmpty()) {
					model.addAttribute("isfav", false);
				} else {
					model.addAttribute("isfav", true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("isfav", true);
		}
		return "book-particular-page";
	}

	@GetMapping("/books/{id}/image")
	public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {

		Optional<Book> book = bookService.findById(id);
		if (book.isPresent() && book.get().getImageFile() != null) {

			Resource file = new InputStreamResource(book.get().getImageFile().getBinaryStream());

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
					.contentLength(book.get().getImageFile().length()).body(file);

		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
