package com.book.subscriber.service;


import com.book.subscriber.entities.BooksDetails;
import com.book.subscriber.entities.Subscriber;

import java.util.List;
import java.util.Optional;

public interface subscriberService {

    List<Subscriber> getAllSubscriber() ;
    Optional<Subscriber> getSubscriberByName(String name,Subscriber subscriber) ;
    Optional<Subscriber> getSubscriberById(int id) ;
    Subscriber addSubscriber(Subscriber subscriber) ;
    Optional<Subscriber> updateSubscriberById(int id, Subscriber subscriber) ;
    void deleteSubscriberById(int id) ;

}
