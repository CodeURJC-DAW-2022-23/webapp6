package es.codeurjc.readmebookstore.controller;

import java.util.Optional;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import jakarta.servlet.http.HttpServletRequest;
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
import es.codeurjc.readmebookstore.repository.UserRepository;
import es.codeurjc.readmebookstore.service.OfferService;
import es.codeurjc.readmebookstore.repository.BookRepository;
import es.codeurjc.readmebookstore.repository.OfferRepository;
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
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OfferRepository offerRepository;

    @PostMapping("/upload-offer/{id}")
    public String uploadedReview(Model model, @PathVariable long id, @RequestParam String edition,
            @RequestParam String text, @RequestParam float price, MultipartFile imageField,
            HttpServletRequest request) throws IOException {

        Book book = bookRepository.findById(id).get();
        String username = request.getUserPrincipal().getName();
        User user = userRepository.findByName(username).orElseThrow();
        Date date = new Date();

        Offer offer = new Offer(date, edition, text, price, book, user);

        if (!imageField.isEmpty()) {
            offer.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
            offer.setImage(true);
        }

        offerRepository.save(offer);
        return "redirect:/book/" + book.getId();
    }

    @GetMapping("/upload-offer-page/{id}")
    public String uploadOffer(Model model, @PathVariable long id) {
        Book book = bookRepository.findById(id).get();
        model.addAttribute("book", book);
        return "upload-offer-page";
    }

    @GetMapping("/offer-page/{id}")
    public String offerPage(Model model, @PathVariable long id) {
        Offer offer = offerRepository.findById(id).get();
        model.addAttribute("offer", offer);
        return "offer-page";
    }

    @GetMapping("/modifyOffer/{id}")
    public String modifyReview(Model model, @PathVariable long id) {
        Optional<Offer> offer = offerRepository.findById(id);
        model.addAttribute("offer", offer.get());
        return "update-offer-page";
    }

    @PostMapping("/updatedOffer/{id}")
    public String updatedReview(Model model, @PathVariable long id, @RequestParam String edition,
            @RequestParam String text, @RequestParam float price) {
        Optional<Offer> offer = offerRepository.findById(id);
        Date date = new Date();
        offer.get().setDate(date);
        offer.get().setDescription(text);
        offer.get().setEdition(edition);
        offer.get().setPrice(price);
        offerRepository.save(offer.get());
        return "redirect:/user-page";
    }

    @GetMapping("/deleteOffer/{id}")
    public String deleteReview(Model model, @PathVariable long id) {
        offerRepository.deleteById(id);
        return "redirect:/user-page";
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