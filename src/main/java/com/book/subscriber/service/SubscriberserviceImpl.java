package com.book.subscriber.service;

import com.book.subscriber.Exception.ResourceNotFoundException;
import com.book.subscriber.entities.Subscriber;
import com.book.subscriber.repository.SubscriberRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubscriberserviceImpl implements subscriberService{
    @Autowired
    SubscriberRepo subscriberRepo;
    @Override
    public List<Subscriber> getAllSubscriber() {
        try {
            List<Subscriber> allSubscribers = subscriberRepo.findAll();
            return allSubscribers.stream().collect(Collectors.toList());
        }catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Subscriber> getSubscriberByName(String name,Subscriber subscriber) {
        Optional<Subscriber> subscriberName=Optional.ofNullable(subscriberRepo.findByName("esa").
                orElseThrow(()->new RuntimeException("Subscriber not present")));

        return null;
    }

    @Override
    public Optional<Subscriber> getSubscriberById(int id) {
        Optional<Subscriber> optionalSubscriber=Optional.ofNullable( subscriberRepo.findById(id).
                orElseThrow(()->new RuntimeException("NO subscriber "+id)));
        return optionalSubscriber;
    }
    @Transactional
    @Override
    public Subscriber addSubscriber(Subscriber subscriber) throws RuntimeException{
        Optional<Subscriber> optionalSubscriberByEmail = subscriberRepo.findByEmail(subscriber.getEmail());
        if(optionalSubscriberByEmail.isPresent()){
//            Subscriber subscriberEmails=optionalSubscriberByEmail.get();
            throw new RuntimeException("Employee with "+subscriber.getEmail()+" already exist");
        }
        Subscriber subs= subscriberRepo.save(subscriber);
        return subs;
    }

    @Override
    public Optional<Subscriber> updateSubscriberById(int id, Subscriber subscriber) {
        Optional<Subscriber> optionalSubscriberById = subscriberRepo.findById(id);
        if (optionalSubscriberById.isPresent()) {
            Subscriber exitSubscriber = optionalSubscriberById.get();
            exitSubscriber.setName(exitSubscriber.getName());
            exitSubscriber.setCity(exitSubscriber.getCity());
            exitSubscriber.setName(exitSubscriber.getName());
            Subscriber saveSubscriber = subscriberRepo.save(exitSubscriber);
            return Optional.of(saveSubscriber);
        } else {
            return Optional.empty();
        }
        }
    @Override
    public void deleteSubscriberById(int id) {
        subscriberRepo.deleteById(id);
    }
}
