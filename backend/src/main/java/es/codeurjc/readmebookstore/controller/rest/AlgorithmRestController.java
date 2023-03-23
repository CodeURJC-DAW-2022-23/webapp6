/*package es.codeurjc.readmebookstore.controller.rest;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.readmebookstore.model.Book;
import es.codeurjc.readmebookstore.model.Offer;
import es.codeurjc.readmebookstore.model.Review;
import es.codeurjc.readmebookstore.service.BookService;
import es.codeurjc.readmebookstore.service.OfferService;
import es.codeurjc.readmebookstore.service.ReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/algorithm")
public class AlgorithmRestController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private OfferService offerService;

    @Operation(summary = "Get algorithm user profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the books", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Book.class))) }),
            @ApiResponse(responseCode = "404", description = "Books not found", content = @Content)
    })
    @GetMapping("/")
    public List<Book> getBooks() {
        return bookService.findAll();
    }

    @Operation(summary = "Get a page of books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the books page", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class, subTypes = {
                            Book.class })) }),
            @ApiResponse(responseCode = "400", description = "Invalid page supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Book page not found", content = @Content)
    })
    @GetMapping("")
    public Page<Book> getBooks(@RequestParam int page) {
        return bookService.findAll(page);
    }

    @Operation(summary = "Get a book by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable long id) {
        Optional<Book> op = bookService.findById(id);
        if (op.isPresent()) {
            Book book = op.get();
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

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
}
*/