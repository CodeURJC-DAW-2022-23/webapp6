package es.codeurjc.readmebookstore.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import es.codeurjc.readmebookstore.model.Book;
import es.codeurjc.readmebookstore.model.Categories;
import es.codeurjc.readmebookstore.model.Offer;
import es.codeurjc.readmebookstore.service.BookService;
import es.codeurjc.readmebookstore.service.CategoriesService;
import es.codeurjc.readmebookstore.service.OfferService;
import es.codeurjc.readmebookstore.service.UserService;

@Controller
public class AlgorithmController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private OfferService offerService;

    @Autowired
    private CategoriesService categoriesService;

    public List<Long> recommendationAlgorithm(Model model, HttpServletRequest request) throws Exception {
        List<Long> recommendedBooks = new ArrayList<>();
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            List<List<String>> categoriesMatrix = ponderationMatrix();
            String sessionName = principal.getName();
            List<List<String>> userMatrix = userMatrix(sessionName);
            List<List<String>> userponderationMatrix = userponderationMatrix(categoriesMatrix, userMatrix);
            List<List<String>> userProfile = userProfile(userponderationMatrix);
            List<List<String>> bookPonder = bookPonder(categoriesMatrix, userProfile);
            List<List<String>> booksRanking = booksRanking(bookPonder);
            recommendedBooks = recommendedBooks(booksRanking);
            return recommendedBooks;
        } else {
            recommendedBooks = recommendedBooksNotLogged();
            return recommendedBooks;
        }
    }

    private List<List<String>> ponderationMatrix() throws Exception {
        int i, j;
        List<Categories> categories = categoriesService.findAll();
        List<Book> books = bookService.findAll();
        List<List<String>> categoriesMatrix = new ArrayList<>();
        categoriesMatrix.add(new ArrayList<>());
        categoriesMatrix.get(0).add("bookid");
        for (j=0; j< categories.size();j++) {
            categoriesMatrix.get(0).add(categories.get(j).getCategorie());
        }
        for (i = 0;i<books.size();i++){
            categoriesMatrix.add(new ArrayList<>());
            categoriesMatrix.get(i+1).add(books.get(i).getId().toString());
            for (j=1; j< categoriesMatrix.get(0).size();j++) {
                if (books.get(i).getCategories().contains(categoriesService.findByName(categoriesMatrix.get(0).get(j)))) {
                    categoriesMatrix.get(i+1).add("1");
                } else {
                    categoriesMatrix.get(i+1).add("0");
                }
            }
        }
        return categoriesMatrix;
    }

    private List<List<String>> userMatrix(String sessionName) throws Exception {
        Long loggedUserId = userService.findByName(sessionName).getId();
        List<Offer> offersbought = offerService.findBooksBought(loggedUserId);
        List<Book> favbooks = bookService.favoritesbooks(loggedUserId);
        List<List<String>> userMatrix = new ArrayList<>();
        int i, j;
        for (i = 0; i < offersbought.size(); i++) {
            userMatrix.add(new ArrayList<>());
            userMatrix.get(i).add(offersbought.get(i).getBook().getId().toString());
            userMatrix.get(i).add("66");
        }
        for (j = 0; j < favbooks.size(); j++) {
            userMatrix.add(new ArrayList<>());
            userMatrix.get(i + j).add(favbooks.get(j).getId().toString());
            userMatrix.get(i + j).add("34");
        }
        return userMatrix;
    }

    private List<List<String>> userponderationMatrix(List<List<String>> categoriesMatrix, List<List<String>> userMatrix) throws Exception {
        int i, j, k, l;
        Boolean match;
        List<List<String>> userponderationMatrix = new ArrayList<>();
        userponderationMatrix.add(new ArrayList<>());
        for (j=0; j< categoriesMatrix.get(0).size();j++) {
            userponderationMatrix.get(0).add(categoriesMatrix.get(0).get(j));
        }
        for (i = 1;i<userMatrix.size() + 1;i++){
            userponderationMatrix.add(new ArrayList<>());
            userponderationMatrix.get(i).add(userMatrix.get(i-1).get(0));
            //optimizable
            k = 0;
            match = true;
            while (match) {
                k++;
                if (userponderationMatrix.get(i).get(0).equals(categoriesMatrix.get(k).get(0))) {
                    match = false;
                }
            }
            //optimizable
            for (j=1; j< categoriesMatrix.get(0).size();j++) {
                if (categoriesMatrix.get(k).get(j).equals("0")) {
                    userponderationMatrix.get(i).add("0");
                } else {
                    userponderationMatrix.get(i).add(userMatrix.get(i-1).get(1));
                }
            }
        }
        return userponderationMatrix;
    }

    private List<List<String>> userProfile(List<List<String>> userponderationMatrix) throws Exception {
        int i, j, k, l;
        int count, totalcount, aux, aux2;
        double pondercount, aux3, totalaux;
        List<List<String>> userProfile = new ArrayList<>();
        List<List<String>> userProfileaux = new ArrayList<>();
        for (j = 0; j < userponderationMatrix.get(0).size() - 1; j++) {
            userProfileaux.add(new ArrayList<>());
            userProfileaux.get(j).add(userponderationMatrix.get(0).get(j+1));
            count = 0;
            for (i = 0; i < userponderationMatrix.size() - 1; i++) {
                aux = Integer.parseInt(userponderationMatrix.get(i+1).get(j+1));
                count = count + aux;
            }
            userProfileaux.get(j).add(Integer.toString(count));
        }
        totalcount = 0;
        for (k = 0; k < userProfileaux.size(); k++) {
            aux2 = Integer.parseInt(userProfileaux.get(k).get(1));
            totalcount = totalcount + aux2;
        }
        pondercount = 0.0;
        totalaux = Double.parseDouble(Integer.toString(totalcount));
        for (l = 0; l < userProfileaux.size(); l++) {
            aux3 = Double.parseDouble(userProfileaux.get(l).get(1));
            pondercount = aux3 / totalaux;
            userProfile.add(new ArrayList<>());
            userProfile.get(l).add(userProfileaux.get(l).get(0));
            userProfile.get(l).add(Double.toString(pondercount));
        }
        return userProfile;
    }

    private List<List<String>> bookPonder(List<List<String>> categoriesMatrix, List<List<String>> userProfile) throws Exception {
        int i, j;
        List<List<String>> bookPonder = new ArrayList<>();
        for (i = 1; i < categoriesMatrix.size(); i++) {
            bookPonder.add(new ArrayList<>());
            bookPonder.get(i - 1).add(categoriesMatrix.get(i).get(0));
            for (j = 1; j < categoriesMatrix.get(0).size(); j++) {
                if (categoriesMatrix.get(i).get(j).equals("0")) {
                    bookPonder.get(i - 1).add("0");
                } else {
                    bookPonder.get(i - 1).add(userProfile.get(j - 1).get(1));
                }
            }
        }
        return bookPonder;
    }

    private List<List<String>> booksRanking(List<List<String>> bookPonder) throws Exception {
        int i, j;
        Double count;
        List<List<String>> booksRanking = new ArrayList<>();
        for (i = 0; i < bookPonder.size(); i++) {
            booksRanking.add(new ArrayList<>());
            booksRanking.get(i).add(bookPonder.get(i).get(0));
            count = 0.0;
            for (j = 1; j < bookPonder.get(0).size(); j++) {
                count = count + Double.parseDouble(bookPonder.get(i).get(j));
            }
            booksRanking.get(i).add(count.toString());
        }
        return booksRanking;
    }

    private List<Long> recommendedBooks(List<List<String>> booksRanking) throws Exception {
        int i, j;
        Double[] booksRankingforsort = new Double[booksRanking.size()];
        for (i = 0; i < booksRankingforsort.length; i++) {
            booksRankingforsort[i] = Double.parseDouble(booksRanking.get(i).get(1));
        }
        Arrays.sort(booksRankingforsort, Collections.reverseOrder());
        List<List<String>> booksRankingsorted = new ArrayList<>();
        List<List<String>> booksRankingaux = new ArrayList<>(booksRanking);
        Boolean match;
        for (i = 0; i < booksRankingforsort.length; i++) {
            //optimizable
            match = true;
            j = 0;
            while (match && (j < booksRanking.size())) {
                if (booksRankingforsort[i].toString().equals(booksRankingaux.get(j).get(1))) {
                    match = false;
                    booksRankingaux.get(j).set(1,"9999");
                }
                j++;
            }
            //optimizable
            booksRankingsorted.add(new ArrayList<>());
            booksRankingsorted.get(i).add(booksRanking.get(j-1).get(0));
            booksRankingsorted.get(i).add(booksRanking.get(j-1).get(1));
        }
        List<Long> recommendedBooks = new ArrayList<>();
        for (i = 0; i < 6; i++) {
            recommendedBooks.add(Long.parseLong(booksRankingsorted.get(i).get(0)));
        }
        return recommendedBooks;
    }

    private List<Long> recommendedBooksNotLogged() throws Exception {
        int i, j;
        List<Book> books = bookService.findAll();
        Double[] booksRankingforsort = new Double[books.size()];
        List<List<String>> booksaux = new ArrayList<>();
        for (i = 0; i < booksRankingforsort.length; i++) {
            Random r = new Random();
            booksRankingforsort[i] = r.nextDouble();
            booksaux.add(new ArrayList<>());
            booksaux.get(i).add(books.get(i).getId().toString());
            booksaux.get(i).add(booksRankingforsort[i].toString());
        }

        Arrays.sort(booksRankingforsort, Collections.reverseOrder());
        List<List<String>> booksRankingsorted = new ArrayList<>();
        Boolean match;
        for (i = 0; i < booksRankingforsort.length; i++) {
            //optimizable
            match = true;
            j = 0;
            while (match && (j < books.size())) {
                if (booksRankingforsort[i].toString().equals(booksaux.get(j).get(1))) {
                    match = false;
                }
                j++;
            }
            //optimizable
            booksRankingsorted.add(new ArrayList<>());
            booksRankingsorted.get(i).add(booksaux.get(j-1).get(0));
            booksRankingsorted.get(i).add(booksaux.get(j-1).get(1));
        }
        List<Long> recommendedBooks = new ArrayList<>();
        for (i = 0; i < 6; i++) {
            recommendedBooks.add(Long.parseLong(booksRankingsorted.get(i).get(0)));
        }
        return recommendedBooks;
    }

}
