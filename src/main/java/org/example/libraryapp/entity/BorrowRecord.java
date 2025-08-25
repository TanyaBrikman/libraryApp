package org.example.libraryapp.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Table(name = "borrow_record")
@Entity
public class BorrowRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    Book book;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    Reader reader;

    @Column(name = "borrow_date")
    LocalDate borrowDate;

    @Column(name = "return_date")
    LocalDate returnDate;
}