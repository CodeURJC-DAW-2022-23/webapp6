package es.codeurjc.readmebookstore.controller;

import java.io.*;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import es.codeurjc.readmebookstore.model.Book;
import es.codeurjc.readmebookstore.model.Offer;
import es.codeurjc.readmebookstore.service.BookService;
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

    public Long[] recommendationAlgorithm(Model model, HttpServletRequest request) throws Exception {
        Long[] recommendedBooks = new Long[6];
        String[][] categoriesMatrix = ponderationMatrix();
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            String sessionName = principal.getName();
            Long[][] userMatrix = userMatrix(sessionName);
            String[][] userponderationMatrix = userponderationMatrix(categoriesMatrix, userMatrix);
            String[][] userProfile = userProfile(userponderationMatrix);
            String[][] bookPonder = bookPonder(categoriesMatrix, userProfile);
            String[][] booksRanking = booksRanking(bookPonder);
            recommendedBooks = recommendedBooks(booksRanking);
            return recommendedBooks;
        } else {
            // not ended
            return recommendedBooks;
        }
    }

    private String[][] ponderationMatrix() throws Exception {
        String st;
        int i, j, k, l, catlen;
        String[] headermatrix = { "bookid", "title", "Policiaca", "Misterio", "Drama", "Novela", "Fantasia",
                "Alta Fantasia", "Fantasia Epica", "Aventuras", "Juvenil", "Literatura clásica", "Epopeya", "Poesia",
                "Politica", "Filosofia", "Elegia", "Literatura universal", "Satira", "Comedia", "Tragedia", "Romance",
                "Novela histórica", "Ficcion" };
        String[][] categoriesMatrix = new String[22][headermatrix.length];
        for (l = 0; l < headermatrix.length; l++) {
            categoriesMatrix[0][l] = headermatrix[l];
        }
        Resource filetxt = new ClassPathResource("/static/Categoria_Libros.txt");
        File file = filetxt.getFile();
        BufferedReader br = new BufferedReader(new FileReader(file));
        i = 0;
        Boolean compare;
        while ((st = br.readLine()) != null) {
            if (i > 0) {
                String[] linepart = st.split(",");
                String[] categories = linepart[2].split(";");
                for (j = 0; j < headermatrix.length; j++) {
                    if (j < 2) {
                        categoriesMatrix[i][j] = linepart[j];
                    } else {
                        k = 0;
                        catlen = categories.length;
                        compare = true;
                        while (compare) {
                            if ((k < catlen) && headermatrix[j].equals(categories[k])) {
                                categoriesMatrix[i][j] = "1.0";
                                compare = false;
                            } else {
                                categoriesMatrix[i][j] = "0.0";
                                compare = false;
                            }
                            k++;
                        }
                    }
                }
            }
            i++;
        }
        br.close();
        return categoriesMatrix;
    }

    private Long[][] userMatrix(String sessionName) throws Exception {
        Long loggedUserId = userService.findByName(sessionName).getId();
        List<Offer> offersbought = offerService.findBooksBought(loggedUserId);
        List<Book> favbooks = bookService.favoritesbooks(loggedUserId);
        Long[][] userMatrix = new Long[offersbought.size() + favbooks.size()][2];
        int i, j;
        for (i = 0; i < offersbought.size(); i++) {
            Long boughtbookId = offersbought.get(i).getBook().getId();
            userMatrix[i][0] = boughtbookId;
            userMatrix[i][1] = (long) 0.66;
        }
        for (j = 0; j < favbooks.size(); j++) {
            Long favbookId = favbooks.get(i).getId();
            userMatrix[i + j][0] = favbookId;
            userMatrix[i + j][1] = (long) 0.34;
        }
        return userMatrix;
    }

    private String[][] userponderationMatrix(String[][] categoriesMatrix, Long[][] userMatrix) throws Exception {
        int i, j, k;
        Boolean match;
        String[][] userponderationMatrix = new String[categoriesMatrix.length - 1][categoriesMatrix[0].length - 2];
        for (i = 0; i < userMatrix.length; i++) {
            match = true;
            j = 0;
            while (match) {
                j++;
                if (userMatrix[i][0].toString() == categoriesMatrix[j][0]) {
                    match = false;
                }
            }
            for (k = 2; k < categoriesMatrix[0].length; k++) {
                if (i == 0) {
                    userponderationMatrix[i][k - 2] = categoriesMatrix[j][k];
                } else {
                    if (categoriesMatrix[i][k] == "0.0") {
                        userponderationMatrix[i][k - 2] = "0.0";
                    } else {
                        userponderationMatrix[i][k - 2] = userMatrix[i][1].toString();
                    }
                }
            }
        }
        return userponderationMatrix;
    }

    private String[][] userProfile(String[][] userponderationMatrix) throws Exception {
        int i, j, k, l;
        Double count, totalcount, pondercount;
        String[][] userProfile = new String[userponderationMatrix[0].length][2];
        for (i = 0; i < userProfile.length; i++) {
            userProfile[i][0] = userponderationMatrix[0][i];
            count = 0.0;
            for (j = 0; j < userponderationMatrix[0].length; j++) {
                count = count + Double.parseDouble(userponderationMatrix[j][i]);
            }
            userProfile[i][1] = count.toString();
        }
        totalcount = 0.0;
        for (k = 0; k < userProfile.length; k++) {
            totalcount = totalcount + Double.parseDouble(userProfile[k][1]);
        }
        pondercount = 0.0;
        for (l = 0; l < userProfile.length; l++) {
            pondercount = Double.parseDouble(userProfile[l][1]) / (Double) totalcount;
            userProfile[l][1] = pondercount.toString();
        }
        return userProfile;
    }

    private String[][] bookPonder(String[][] categoriesMatrix, String[][] userProfile) throws Exception {
        int i, j;
        String[][] bookPonder = new String[categoriesMatrix[0].length][2];
        for (i = 0; i < categoriesMatrix.length; i++) {
            bookPonder[i][0] = categoriesMatrix[i][0];
            for (j = 2; j < categoriesMatrix[0].length; j++) {
                if (i == 0) {
                    bookPonder[i][j - 1] = categoriesMatrix[i][j];
                } else {
                    if (categoriesMatrix[i][j] == "0.0") {
                        bookPonder[i][j - 1] = "0.0";
                    } else {
                        bookPonder[i][j - 1] = userProfile[j - 2][1];
                    }
                }
            }
        }
        return bookPonder;
    }

    private String[][] booksRanking(String[][] bookPonder) throws Exception {
        int i, j;
        Double count;
        String[][] booksRanking = new String[bookPonder[0].length][2];
        for (i = 0; i < bookPonder.length; i++) {
            booksRanking[i][0] = bookPonder[i][0];
            count = 0.0;
            for (j = 0; j < bookPonder[0].length; j++) {
                count = count + Double.parseDouble(bookPonder[i][j]);
            }
            booksRanking[i][1] = count.toString();
        }
        return booksRanking;
    }

    private Long[] recommendedBooks(String[][] booksRanking) throws Exception {
        int i, j;
        Double[] booksRankingforsort = new Double[booksRanking.length];
        for (i = 0; i < 6; i++) {
            booksRankingforsort[i] = Double.parseDouble(booksRanking[i][1]);
        }
        Arrays.sort(booksRankingforsort);
        String[][] booksRankingsorted = new String[booksRanking.length][2];
        Boolean match;
        for (i = 0; i < booksRanking.length; i++) {
            match = true;
            j = 0;
            while (match) {
                j++;
                if (booksRankingforsort[i].toString() == booksRanking[j][1]) {
                    match = false;
                }
            }
            booksRankingsorted[i][0] = booksRanking[j][0];
            booksRankingsorted[i][1] = booksRanking[j][1];
        }
        Long[] recommendedBooks = new Long[6];
        for (i = 0; i < 6; i++) {
            recommendedBooks[i] = Long.parseLong(booksRankingsorted[i][0]);
        }
        return recommendedBooks;
    }

}
