package org.example.libraryapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;

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
   Instant borrowDate;

    @Column(name = "return_date")
    Instant returnDate;


    public BorrowRecord(Book book, Reader reader) {
        this.book = book;
        this.reader = reader;
        this.borrowDate = Instant.now();
        this.returnDate = null;
    }

    public BorrowRecord() {
    }
}