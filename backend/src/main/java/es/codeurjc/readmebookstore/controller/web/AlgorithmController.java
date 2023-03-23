package es.codeurjc.readmebookstore.controller.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import es.codeurjc.readmebookstore.service.AlgorithmService;

@Controller
public class AlgorithmController {

    @Autowired
    private AlgorithmService algorithmService;

    public List<Long> recommendationAlgorithm(Model model, HttpServletRequest request) throws Exception {
        List<Long> recommendedBooks = new ArrayList<>();
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            List<List<String>> categoriesMatrix = algorithmService.ponderationMatrix();
            String sessionName = principal.getName();
            List<List<String>> userMatrix = algorithmService.userMatrix(sessionName);
            List<List<String>> userponderationMatrix = algorithmService.userponderationMatrix(categoriesMatrix, userMatrix);
            List<List<String>> userProfile = algorithmService.userProfile(userponderationMatrix);
            List<List<String>> bookPonder = algorithmService.bookPonder(categoriesMatrix, userProfile);
            List<List<String>> booksRanking = algorithmService.booksRanking(bookPonder);
            recommendedBooks = algorithmService.recommendedBooks(booksRanking);
            return recommendedBooks;
        } else {
            recommendedBooks = algorithmService.recommendedBooksNotLogged();
            return recommendedBooks;
        }
    }

}
