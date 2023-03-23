package es.codeurjc.readmebookstore.controller.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.readmebookstore.service.BookService;
import es.codeurjc.readmebookstore.service.OfferService;
import es.codeurjc.readmebookstore.service.ReviewService;
import es.codeurjc.readmebookstore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsRestController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private OfferService offerService;

    @Autowired
    private UserService userService;

    @Operation(summary = "Get the statistics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the statistics", content = {
                    @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "404", description = "Statistics not found", content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<Map<String, Integer>> getStatistics() {
        Map<String, Integer> mapa = new HashMap<>();
        mapa.put("Number of users", userService.findAll().size());
        mapa.put("Number of books", bookService.findAll().size());
        mapa.put("Number of offers", offerService.findAll().size());
        mapa.put("Number of reviews", reviewService.findAll().size());
        return ResponseEntity.ok(mapa);
    }

}
