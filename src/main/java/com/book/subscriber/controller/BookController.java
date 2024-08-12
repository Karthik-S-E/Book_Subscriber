package com.book.subscriber.controller;

import com.book.subscriber.Exception.ResourceNotFoundException;
import com.book.subscriber.entities.BooksDetails;
import com.book.subscriber.service.BookServiceImpl;
import com.book.subscriber.service.Bookservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
public class BookController {
    @Autowired
    private Bookservice bookservice;



//    public BookController(BookServiceImpl bookservice) {
//        this.bookservice = bookservice;
//    }


    @GetMapping("/books")
    public ResponseEntity<List<BooksDetails>> getAllBooks() {
        List<BooksDetails> allBooks = bookservice.getAllBooks();
        return ResponseEntity.ok(allBooks);
    }
    @GetMapping("/books/bookId/{id}")
    public ResponseEntity<BooksDetails> showBookById(@PathVariable int id) {
        Optional<BooksDetails> book = bookservice.getBookById(id);

        return book.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @PostMapping("/addbook")
    public ResponseEntity<String> addBooks(@RequestBody BooksDetails newBooks) {
        BooksDetails saveBooks = bookservice.addBook(newBooks);

//        if (saveBooks.getBookName() == saveBooks.getBookName() && saveBooks.getPublisherName() == saveBooks.getBookName()) {
//             saveBooks = bookservice.addBook(newBooks);
//            return ResponseEntity.ok("not ");
//        } else
            return ResponseEntity.ok("Book saved successfully");

    }

    @PutMapping("/books/update/{id}")
    public  ResponseEntity<BooksDetails> updateBook(@PathVariable int id,@RequestBody BooksDetails booksDetails ){
        Optional<BooksDetails> updation =bookservice.updateBookById(id,booksDetails);
        return updation.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        try {
            bookservice.deleteBookById(id);
            return ResponseEntity.ok("Deleted");
        }catch (ResourceNotFoundException e)
        {
            throw  new ResourceNotFoundException("give Valid ID");
        }

    }

}
