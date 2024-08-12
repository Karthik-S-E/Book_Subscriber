package com.book.subscriber.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="BooksDetails")
public class BooksDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name= "BOOK_ID")
    int bookId;
    @Column(name= "BOOK_NAME")
    String bookName;

    @Column(name= "PUBLISHER")
    String publisherName;
}
