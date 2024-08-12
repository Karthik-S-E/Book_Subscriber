package com.book.subscriber.service;
import com.book.subscriber.Exception.ResourceNotFoundException;
import com.book.subscriber.entities.BooksDetails;
import com.book.subscriber.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements Bookservice {
    @Autowired
    BookRepo bookRepo;

    public List<BooksDetails> getAllBooks() {
        List<BooksDetails> bookList = bookRepo.findAll();
        return bookList;
    }

    @Override
    public Optional<BooksDetails> getBookById(int id) {
        Optional<BooksDetails> booksDetails = Optional.ofNullable(bookRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Failed to get Book Might be Wrong Id " + id)));
        return booksDetails;

    }

    @Override
    public BooksDetails addBook(BooksDetails booksDetails) {
        return bookRepo.save(booksDetails);
    }

    @Override
    public Optional<BooksDetails> updateBookById(int id, BooksDetails booksDetails) {
        try {
            Optional<BooksDetails> bookForUpdate = bookRepo.findById(id);
            if (bookForUpdate.isPresent()) {
                BooksDetails existingBook = bookForUpdate.get();
                existingBook.setBookName(booksDetails.getBookName());
                existingBook.setPublisherName(booksDetails.getPublisherName());
                BooksDetails saveBook = bookRepo.save(existingBook);
                return Optional.of(saveBook);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new RuntimeException("fail to update" + e.getMessage());
        }
    }

    @Override
    public void deleteBookById(int id) {
        if (bookRepo.existsById(id)) {
            bookRepo.deleteById(id);

        } else {
            throw new ResourceNotFoundException("Id not present");
        }
    }
}

