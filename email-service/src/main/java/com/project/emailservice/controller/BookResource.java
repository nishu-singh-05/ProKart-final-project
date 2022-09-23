package com.project.emailservice.controller;

import com.project.emailservice.model.Ticket;
import com.project.emailservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookResource {

    @Autowired
    private BookService bookService;

    @PostMapping("/save")
    public String saveBook(@RequestBody Ticket book) {
        return bookService.saveBook(book);
    }

    @GetMapping("/searchBook/{bookId}")
    public Optional<Ticket> getBook(@PathVariable int bookId) {
        return bookService.getBook(bookId);
    }
    @DeleteMapping("/deleteBook/{bookId}")
    public List<Ticket> deleteBook(@PathVariable int bookId) {
        return bookService.removeBook(bookId);
    }

}
