package com.book.subscriber.repository;

import com.book.subscriber.entities.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriberRepo extends JpaRepository<Subscriber, Integer> {
    Optional<Subscriber>  findByName(String subscriber);
    Optional<Subscriber>  findByEmail(String email);

}
