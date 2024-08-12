package com.book.subscriber.service;

import com.book.subscriber.entities.BooksDetails;

import java.util.List;
import java.util.Optional;

public interface Bookservice {

    List<BooksDetails> getAllBooks() ;
    Optional<BooksDetails> getBookById(int id) ;
    BooksDetails addBook(BooksDetails booksDetails) ;
    Optional<BooksDetails> updateBookById(int id, BooksDetails booksDetails) ;
    void deleteBookById(int id) ;

}
