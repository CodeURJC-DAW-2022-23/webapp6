package es.codeurjc.readmebookstore.controller.web;

import java.util.Optional;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import es.codeurjc.readmebookstore.model.User;
import es.codeurjc.readmebookstore.model.Book;
import es.codeurjc.readmebookstore.model.Offer;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import es.codeurjc.readmebookstore.service.OfferService;
import es.codeurjc.readmebookstore.service.UserService;
import es.codeurjc.readmebookstore.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class OfferController {

    @Autowired
    private OfferService offerService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @PostMapping("/books/{id}/offers")
    public String uploadedReview(Model model, @PathVariable long id, @RequestParam String edition,
            @RequestParam String text, @RequestParam float price, MultipartFile imageField,
            HttpServletRequest request) throws IOException {

        Book book = bookService.findById(id).get();
        String username = request.getUserPrincipal().getName();
        User user = userService.findByName(username);
        Date date = new Date();

        Offer offer = new Offer(date, edition, text, price, book, user, false, null);

        if (!imageField.isEmpty()) {
            offer.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
            offer.setImage(true);
        }

        offerService.save(offer);
        return "redirect:/book/" + book.getId();
    }

    @GetMapping("/upload-offer/{id}")
    public String uploadOffer(Model model, @PathVariable long id) {
        Book book = bookService.findById(id).get();
        model.addAttribute("book", book);
        return "upload-offer-page";
    }

    @GetMapping("/offers/{id}")
    public String offerPage(Model model, @PathVariable long id, HttpServletRequest request) {

        Offer offer = offerService.findById(id).get();

        String vendorName = offer.getSeller().getName();
        Boolean ownOffer = false;
        Boolean soldOffer = offer.getSold();

        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            String activeUserName = request.getUserPrincipal().getName();
            ownOffer = activeUserName.equals(vendorName);
        }

        model.addAttribute("sold", soldOffer);
        model.addAttribute("own", ownOffer);
        model.addAttribute("offer", offer);
        return "offer-page";
    }

    @GetMapping("/update-offer/{id}")
    public String loadUpdateOfferPage(Model model, @PathVariable long id) {
        Optional<Offer> offer = offerService.findById(id);
        model.addAttribute("offer", offer.get());
        return "update-offer-page";
    }

    @PostMapping("/offers/{id}/update")
    public String updatedOffer(Model model, @PathVariable long id, @RequestParam String edition,
            @RequestParam String text, @RequestParam float price, MultipartFile imageField, HttpServletRequest request) throws IOException {
        Offer offer = offerService.findById(id).get();
        User user = userService.findByName(request.getUserPrincipal().getName());
        if (user.getId()== offer.getSeller().getId()){
            Date date = new Date();
            offer.setDate(date);
            offer.setDescription(text);
            offer.setEdition(edition);
            offer.setPrice(price);
            offerService.save(offer);
            try {
                offerService.updateImage(offer, false, imageField);
            } catch (Exception e) {
                return "redirect:/offers/" + offer.getId();
            }
            offerService.save(offer);
        }
        return "redirect:/offers/" + offer.getId();
    }

    @GetMapping("/offers/{id}/delete")
    public String deleteOffer(Model model, @PathVariable long id, HttpServletRequest request) {
        Offer offer = offerService.findById(id).get();
        User user = userService.findByName(request.getUserPrincipal().getName());
        if (user.getId()== offer.getSeller().getId()){
            offerService.delete(id);
        }
        return "redirect:/user";
    }

    @GetMapping("/offers/{id}/checkout")
    public String loadCheckoutPage(Model model, @PathVariable long id) {
        Offer offer = offerService.findById(id).get();
        model.addAttribute("offer", offer);
        return "checkout-page";
    }

    @GetMapping("/offers/{id}/sold")
    public String buyOffer(Model model, @PathVariable long id, HttpServletRequest request) {
        Offer offer = offerService.findById(id).get();
        if (!offer.getSold()){
            User buyer = userService.findByName(request.getUserPrincipal().getName());
            offer.setSold(true);
            offer.setBuyer(buyer);
            offerService.save(offer);
        }
        return "redirect:/user";
    }

    @GetMapping("/offers/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {

        Offer offer = offerService.findById(id).get();

        if (offer.getImageFile() != null) {
            Resource file = new InputStreamResource(offer.getImageFile().getBinaryStream());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(offer.getImageFile().length()).body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}