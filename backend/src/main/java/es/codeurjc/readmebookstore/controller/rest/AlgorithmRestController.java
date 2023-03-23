package es.codeurjc.readmebookstore.controller.rest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.readmebookstore.model.Book;
import es.codeurjc.readmebookstore.service.BookService;
import es.codeurjc.readmebookstore.service.AlgorithmService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/algorithm")
public class AlgorithmRestController {

        @Autowired
        private BookService bookService;

        @Autowired
        private AlgorithmService algorithmService;

        @Operation(summary = "Get a page of books")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Found the books page", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class, subTypes = {
                                                        Book.class })) }),
                        @ApiResponse(responseCode = "400", description = "Invalid page supplied", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Book page not found", content = @Content)
        })
        @GetMapping("")
        private ResponseEntity<List<Book>> recommendationAlgorithm(HttpServletRequest request) throws Exception {
                List<Book> recommendedBooks = new ArrayList<>();
                List<Long> recommendedBooksIds = new ArrayList<>();
                Principal principal = request.getUserPrincipal();
                if (principal != null) {
                        List<List<String>> categoriesMatrix = algorithmService.ponderationMatrix();
                        String sessionName = principal.getName();
                        List<List<String>> userMatrix = algorithmService.userMatrix(sessionName);
                        List<List<String>> userponderationMatrix = algorithmService
                                        .userponderationMatrix(categoriesMatrix, userMatrix);
                        List<List<String>> userProfile = algorithmService.userProfile(userponderationMatrix);
                        List<List<String>> bookPonder = algorithmService.bookPonder(categoriesMatrix, userProfile);
                        List<List<String>> booksRanking = algorithmService.booksRanking(bookPonder);
                        recommendedBooksIds = algorithmService.recommendedBooks(booksRanking);
                } else {
                        recommendedBooksIds = algorithmService.recommendedBooksNotLogged();
                }
                for (int i = 0; i < recommendedBooksIds.size(); i++) {
			Book book = bookService.findById(recommendedBooksIds.get(i)).get();
			recommendedBooks.add(book);
		        }
                        if (recommendedBooks.isEmpty()) {
                                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                            } else {
                                return new ResponseEntity<>(recommendedBooks, HttpStatus.OK);
                            }
        }

}