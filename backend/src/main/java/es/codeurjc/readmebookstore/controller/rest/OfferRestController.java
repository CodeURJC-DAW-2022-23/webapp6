package es.codeurjc.readmebookstore.controller.rest;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.readmebookstore.model.Offer;
import es.codeurjc.readmebookstore.model.User;
import es.codeurjc.readmebookstore.service.OfferService;
import es.codeurjc.readmebookstore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api/offers")
public class OfferRestController {

    @Autowired
    private OfferService offerService;

    @Autowired
    private UserService userService;

    @Operation(summary = "Get all Offers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the offers", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Offer.class))) }),
            @ApiResponse(responseCode = "404", description = "Offers not found", content = @Content)
    })
    @GetMapping("/")
    public List<Offer> getOffers() {
        return offerService.findAll();
    }

    @Operation(summary = "Get a page of offers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the offers page", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class, subTypes = {
                            Offer.class })) }),
            @ApiResponse(responseCode = "400", description = "Invalid page supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Offer page not found", content = @Content)
    })
    @GetMapping("")
    public Page<Offer> getOffers(@RequestParam int page) {
        return offerService.findAll(page);
    }

    @Operation(summary = "Get a Offer by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the offer", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Offer.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Offernot found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Offer> getOffer(@PathVariable long id) {
        Optional<Offer> op = offerService.findById(id);
        if (op.isPresent()) {
            Offer offer = op.get();
            return new ResponseEntity<>(offer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "The image of a specified offer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the image", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Resource.class))) }),
            @ApiResponse(responseCode = "404", description = "Image not found", content = @Content)
    })
    @GetMapping("/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {
        Optional<Offer> offer = offerService.findById(id);
        if (offer.isPresent() && offer.get().getImageFile() != null) {
            Resource file = new InputStreamResource(offer.get().getImageFile().getBinaryStream());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(offer.get().getImageFile().length()).body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


        /////////////////////   DELETES  ////////////////////////////////////

    // DOES NOT WORK YET (method not allowed)
    @Operation(summary = "Delete a offer by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Offer deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Offer.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Offer not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Offer> deleteOffer(@PathVariable long id) {
        Offer offer = offerService.findById(id).get();
        if (offer != null) {
            offerService.delete(id);
            return ResponseEntity.ok(offer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    /////////////////////   PUTS  ////////////////////////////////////

    // DOES NOT WORK YET (method not allowed)R
    @Operation(summary = "Update a offer ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Offer updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Offer.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login", content = @Content),
            @ApiResponse(responseCode = "404", description = "Offer not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Offer> updateOffer(@PathVariable long id,
            @RequestBody Offer updatedOffer) throws SQLException {
        Offer offer = offerService.findById(id).get();

        if (offer != null) {
                if (updatedOffer.getImage()) {
                    Offer dbOffer = offerService.findById(id).orElseThrow();
                    if (dbOffer.getImage()) {
                        updatedOffer.setImageFile(BlobProxy.generateProxy(dbOffer.getImageFile().getBinaryStream(),
                                dbOffer.getImageFile().length()));
                    }
                }
                updatedOffer.setId(id);
                updatedOffer.setSeller(offer.getSeller());
                updatedOffer.setBook(offer.getBook());
                updatedOffer.setSold(offer.getSold());
                Date date = new Date();
                updatedOffer.setDate(date);
                offerService.save(updatedOffer);
                return ResponseEntity.ok(updatedOffer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Update a offer ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Offer updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Offer.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login", content = @Content),
            @ApiResponse(responseCode = "404", description = "Offer not found", content = @Content)
    })
    @PutMapping("/{id}/sold")
    public ResponseEntity<Offer> SoldOffer(@PathVariable long id,
            @RequestBody Offer updatedOffer, HttpServletRequest request) throws SQLException {
        Offer offer = offerService.findById(id).get();

        if (offer != null) {
            if (!offer.getSold()){
                User buyer = userService.findByName(request.getUserPrincipal().getName());
                offer.setSold(true);
                offer.setBuyer(buyer);
                offerService.save(offer);
                return ResponseEntity.ok(offer);
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }
        else {
            return ResponseEntity.notFound().build();
        }
    
        }

}
