package es.codeurjc.readmebookstore.controller;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import es.codeurjc.readmebookstore.model.Book;
import es.codeurjc.readmebookstore.model.Offer;
import es.codeurjc.readmebookstore.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import es.codeurjc.readmebookstore.service.BookService;
import es.codeurjc.readmebookstore.service.UserService;
import es.codeurjc.readmebookstore.service.OfferService;
import es.codeurjc.readmebookstore.repository.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BookController extends AlgorithmController{

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

	@Autowired
    private OfferService offerService;

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
        model.addAttribute("books", bookService.findAll(0));
		model.addAttribute("isbooksempty", bookService.findAll(0).isEmpty());

		model.addAttribute("currentPage", 0);
		return "books-general-page";
	}

    @GetMapping("/book/{id}")
	public String showBook(Model model, @PathVariable long id, HttpServletRequest request) {
        Optional<Book> book = bookRepository.findById(id);
		List<Offer> offers = offerService.findOffersNotSoldByBook(id);
        model.addAttribute("book", book.get());
        model.addAttribute("reviews", book.get().getReviews());
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

	@GetMapping("/search")
    public String doSearch(Model model, @RequestParam String searchtext, HttpServletRequest request) {
		String result = "false";
		result = doSearchTitle(model, searchtext, request);
        if (result != "false") {
            return result;
        } else {
			result = doSearchAuthor(model, searchtext);
			if (result != "false") {
				return result;
			} else {
				result = doSearchGenre(model, searchtext);
				if (result != "false") {
					return result;
				} else {
					result = doSearchPartial(model, searchtext);
					if (result != "false") {
						return result;
					} else {
						//return "error-search"; No has encontrado nada
						return "error-page";
					}
				}
			}
		}
    }

	private String doSearchTitle(Model model, @RequestParam String title, HttpServletRequest request) {
		String result = "false";
		try {
            Optional<Book> booktitle = bookService.findByTitle(title);
            if(booktitle.get().getId() != null) {	
				return showBook(model, booktitle.get().getId(), request);
            }
            else {
                result = "false";
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "false";
        }
		return result;
	}

	private String doSearchAuthor(Model model, @RequestParam String author) {
		String result = "false";
		try {
            List<Book> bookauthor = bookService.findByAuthor(author);
            if (bookauthor.size() > 0) {
				model.addAttribute("books", bookauthor);
				return "books-general-page";
			} else {
				return "false";
			}
        } catch (Exception e) {
            e.printStackTrace();
            result = "false";
        }
		return result;
	}

	private String doSearchGenre(Model model, @RequestParam String genre) {
		String result = "false";
		try {
            List<Book> bookgenre = bookService.findByPartial(genre);
            if (bookgenre.size() > 0) {
				model.addAttribute("books", bookgenre);
				return "books-general-page";
			} else {
				return "false";
			}
        } catch (Exception e) {
            e.printStackTrace();
            result = "false";
        }
		return result;
	}

	private String doSearchPartial(Model model, @RequestParam String partial) {
		String result = "false";
		try {
            List<Book> bookpartial = bookService.findByGenre(partial);
            if (bookpartial.size() > 0) {
				model.addAttribute("books", bookpartial);
				return "books-general-page";
			} else {
				return "false";
			}
        } catch (Exception e) {
            e.printStackTrace();
            result = "false";
        }
		return result;
	}

	@GetMapping("/algorithmbooks")
	public String showalgoritgmBooks(Model model, HttpServletRequest request) throws Exception {
		//Long [] recommendedBooks = recommendationAlgorithm (model, request);
		Long [] recommendedBooks = { (long)0, (long)1, (long)2, (long)3, (long)4, (long)5};
        Long bookid = recommendedBooks[0];
		model.addAttribute("algoritbooks", bookService.findById(bookid));
		return "redirect:/search";
	}

}
