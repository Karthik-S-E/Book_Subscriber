package com.book.subscriber.controller;

import com.book.subscriber.Exception.ResourceNotFoundException;
import com.book.subscriber.entities.BooksDetails;
import com.book.subscriber.entities.Subscriber;
import com.book.subscriber.service.Bookservice;
import com.book.subscriber.service.subscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SubscriberController {
    @Autowired
    private subscriberService subscriberService;
//    public BookController(BookServiceImpl bookservice) {
//        this.bookservice = bookservice;
//    }

    @GetMapping("/subs")
    public ResponseEntity<List<Subscriber>> getAllSubs() {
        List<Subscriber> allSubscribers = subscriberService.getAllSubscriber();
        return ResponseEntity.ok(allSubscribers);
    }
    @GetMapping("/subs/subsId/{id}")
    public ResponseEntity<Subscriber> showBookById(@PathVariable int id) {
        Optional<Subscriber> book = subscriberService.getSubscriberById(id);

        return book.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @PostMapping("/addSubs")
    public ResponseEntity<String> addBooks(@RequestBody Subscriber newSubs) {
        Subscriber saveSubs = subscriberService.addSubscriber(newSubs);


//        if (saveBooks.getBookName() == saveBooks.getBookName() && saveBooks.getPublisherName() == saveBooks.getBookName()) {
//             saveBooks = bookservice.addBook(newBooks);
//            return ResponseEntity.ok("not ");
//        } else
            return ResponseEntity.ok("Subscriber saved successfully");

    }

    @PutMapping("/subs/update/{id}")
    public  ResponseEntity<Subscriber> updateSubscriber(@PathVariable int id,@RequestBody Subscriber subscriber ){
        Optional<Subscriber> updation =subscriberService.updateSubscriberById( id, subscriber);
        return updation.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/subs/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        try {
            subscriberService.deleteSubscriberById(id);
            return ResponseEntity.ok("Deleted");
        }catch (ResourceNotFoundException e)
        {
            throw  new ResourceNotFoundException("Give Valid ID");
        }
    }

}
