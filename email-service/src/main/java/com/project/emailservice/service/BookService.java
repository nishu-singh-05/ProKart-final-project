package com.project.emailservice.service;


import com.project.emailservice.model.Ticket;
import com.project.emailservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public String saveBook(Ticket book) {

        // validations before saving any data to DB..
        if(book == null) {
            return "Invalid Book Object";
        }
        bookRepository.save(book);
        return "book save with id"+ book.getBookId();
    }

    public Optional<Ticket> getBook(int bookId) {
        return bookRepository.findById(bookId);
    }

    public List<Ticket> removeBook(int bookId) {
        bookRepository.deleteById(bookId);
        return bookRepository.findAll();
    }

}
