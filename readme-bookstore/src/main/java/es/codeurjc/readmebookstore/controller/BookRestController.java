package es.codeurjc.readmebookstore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.readmebookstore.model.Book;
import es.codeurjc.readmebookstore.service.BookService;

/* @RestController
@RequestMapping("/books")
public class BookRestController {
    

    @Autowired
    private BookService bookService;
    
    

    @GetMapping("")
    public ResponseEntity<Page<Book>> showMore(Model model, HttpServletRequest request, HttpServletResponse response) {


        int pageRequested = 0;
        String numPage = request.getParameter("numPage");
        if (numPage != null) {
            pageRequested = Integer.parseInt(numPage);
        }

            Page<Book> page = bookService.findAll(pageRequested);
            return ResponseEntity.ok(page);

    }

} */