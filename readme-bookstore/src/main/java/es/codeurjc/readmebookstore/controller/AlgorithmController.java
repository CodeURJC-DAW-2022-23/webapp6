package es.codeurjc.readmebookstore.controller;

import java.io.*;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
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
            String[][] userMatrix = userMatrix(sessionName);
            String[][] userponderationMatrix = userponderationMatrix(categoriesMatrix, userMatrix);
            String[][] userProfile = userProfile(userponderationMatrix);
            String[][] bookPonder = bookPonder(categoriesMatrix, userProfile);
            String[][] booksRanking = booksRanking(bookPonder);
            recommendedBooks = recommendedBooks(booksRanking);
            return recommendedBooks;
        } else {
            // not ended
            //return recommendedBooks;
            Long[] recommendedBooksIds = { (long) 3, (long) 2, (long) 3, (long) 4, (long) 5, (long) 6, (long) 16 };
            return recommendedBooksIds;
        }
    }

    private String[][] ponderationMatrix() throws Exception {
        String st;
        int i, j, k, l, catlen, count;
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
                count = 0;
                for (j = 0; j < headermatrix.length; j++) {
                    if (j < 2) {
                        categoriesMatrix[i][j] = linepart[j];
                    } else {
                        k = 0;
                        catlen = categories.length;
                        compare = true;
                        while ((count < catlen) && compare && k < headermatrix.length) {
                            if ((k < catlen) && headermatrix[j].equals(categories[k])) {
                                compare = false;
                                count++;
                            }
                            k++;
                        }
                        if (compare) {
                            categoriesMatrix[i][j] = "0";
                        } else {
                            categoriesMatrix[i][j] = "1";
                        }
                    }
                }
            }
            i++;
        }
        br.close();
        return categoriesMatrix;
    }

    private String[][] userMatrix(String sessionName) throws Exception {
        Long loggedUserId = userService.findByName(sessionName).getId();
        List<Offer> offersbought = offerService.findBooksBought(loggedUserId);
        List<Book> favbooks = bookService.favoritesbooks(loggedUserId);
        String[][] userMatrix = new String[offersbought.size() + favbooks.size()][2];
        int i, j;
        for (i = 0; i < offersbought.size(); i++) {
            Long boughtbookId = offersbought.get(i).getBook().getId();
            userMatrix[i][0] = boughtbookId.toString();
            userMatrix[i][1] = "66";
        }
        for (j = 0; j < favbooks.size(); j++) {
            Long favbookId = favbooks.get(j).getId();
            userMatrix[i + j][0] = favbookId.toString();
            userMatrix[i + j][1] = "34";
        }
        return userMatrix;
    }

    private String[][] userponderationMatrix(String[][] categoriesMatrix, String[][] userMatrix) throws Exception {
        int i, j, k, l;
        Boolean match;
        String[][] userponderationMatrix = new String[userMatrix.length + 1][categoriesMatrix[0].length - 1];
        for (l = 0; l < categoriesMatrix[0].length; l++) {
            if (l < 1) {
                userponderationMatrix[0][l] = categoriesMatrix[0][l];
            } else {
                if (l > 1) {
                    userponderationMatrix[0][l - 1] = categoriesMatrix[0][l];
                }
            }
        }
        for (i = 1; i < userMatrix.length + 1; i++) {
            match = true;
            userponderationMatrix[i][0] = userMatrix[i - 1][0];
            j = 0;
            while (match) {
                j++;
                if (userponderationMatrix[i][0].equals(categoriesMatrix[j][0])) {
                    match = false;
                }
            }
            for (k = 2; k < categoriesMatrix[0].length; k++) {
                if (categoriesMatrix[j][k].equals("0")) {
                    userponderationMatrix[i][k - 1] = "0";
                } else {
                    userponderationMatrix[i][k - 1] = userMatrix[i - 1][1];
                }
            }
        }
        return userponderationMatrix;
    }

    private String[][] userProfile(String[][] userponderationMatrix) throws Exception {
        int i, j, k, l;
        int count, totalcount, aux, aux2;
        double pondercount, aux3, totalaux;
        String[][] userProfile = new String[userponderationMatrix[0].length - 1][2];
        for (j = 0; j < userProfile.length; j++) {
            userProfile[j][0] = userponderationMatrix[0][j + 1];
            count = 0;
            for (i = 0; i < userponderationMatrix.length - 1; i++) {
                aux = Integer.parseInt(userponderationMatrix[i + 1][j + 1]);
                count = count + aux;
            }
            userProfile[j][1] = Integer.toString(count);
        }
        totalcount = 0;
        for (k = 0; k < userProfile.length; k++) {
            aux2 = Integer.parseInt(userProfile[k][1]);
            totalcount = totalcount + aux2;
        }
        pondercount = 0.0;
        totalaux = Double.parseDouble(Integer.toString(totalcount));
        for (l = 0; l < userProfile.length; l++) {
            aux3 = Double.parseDouble(userProfile[l][1]);
            pondercount = aux3 / totalaux;
            userProfile[l][1] = Double.toString(pondercount);
        }
        return userProfile;
    }

    private String[][] bookPonder(String[][] categoriesMatrix, String[][] userProfile) throws Exception {
        int i, j;
        String[][] bookPonder = new String[categoriesMatrix.length - 1][categoriesMatrix[0].length - 1];
        for (i = 1; i < categoriesMatrix.length; i++) {
            bookPonder[i - 1][0] = categoriesMatrix[i][0];
            for (j = 2; j < categoriesMatrix[0].length; j++) {
                if (categoriesMatrix[i][j].equals("0")) {
                    bookPonder[i - 1][j - 1] = "0";
                } else {
                    bookPonder[i - 1][j - 1] = userProfile[j - 2][1];
                }
            }
        }
        return bookPonder;
    }

    private String[][] booksRanking(String[][] bookPonder) throws Exception {
        int i, j;
        Double count;
        String[][] booksRanking = new String[bookPonder.length][2];
        for (i = 0; i < bookPonder.length; i++) {
            booksRanking[i][0] = bookPonder[i][0];
            count = 0.0;
            for (j = 1; j < bookPonder[0].length; j++) {
                count = count + Double.parseDouble(bookPonder[i][j]);
            }
            booksRanking[i][1] = count.toString();
        }
        return booksRanking;
    }

    private Long[] recommendedBooks(String[][] booksRanking) throws Exception {
        int i, j;
        Double[] booksRankingforsort = new Double[booksRanking.length];
        for (i = 0; i < booksRankingforsort.length; i++) {
            booksRankingforsort[i] = Double.parseDouble(booksRanking[i][1]);
        }
        Arrays.sort(booksRankingforsort, Collections.reverseOrder());
        String[][] booksRankingsorted = new String[booksRanking.length][2];
        Boolean match;
        for (i = 0; i < booksRankingforsort.length; i++) {
            match = true;
            j = 0;
            while (match && (j < booksRanking.length)) {
                if (booksRankingforsort[i].toString().equals(booksRanking[j][1])) {
                    match = false;
                }
                j++;  
            }  
            booksRankingsorted[i][0] = booksRanking[j-1][0];
            booksRankingsorted[i][1] = booksRanking[j-1][1];
        }
        Long[] recommendedBooks = new Long[6];
        for (i = 0; i < 6; i++) {
            recommendedBooks[i] = Long.parseLong(booksRankingsorted[i][0]);
        }
        return recommendedBooks;
    }

}
