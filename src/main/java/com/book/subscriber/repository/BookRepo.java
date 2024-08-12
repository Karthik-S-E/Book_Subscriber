package com.book.subscriber.repository;

import com.book.subscriber.entities.BooksDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<BooksDetails,Integer>{

}




