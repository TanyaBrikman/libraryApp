package org.example.libraryapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "author", nullable = false)
    String author;

    @Column(name = "year_of_publication", nullable = false)
    Integer yearOfPublication;

    @Column(name = "isbn", nullable = false, unique = true)
    @Size(min = 17, max = 17)
    @Pattern(regexp = "^\\d{3}-\\d{2}-\\d{5}-\\d{2}-\\d$", message = "Формат: XXX-XX-XXXXX-XX-X, где X это число")
    String isbn;

    @Column(name = "access", nullable = false)
    Boolean access = true;

    public Book(String title, String author, Integer yearOfPublication, String isbn) {
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.isbn = isbn;
        this.access = true;
    }

    public Book(String title, String author, Integer yearOfPublication ) {
        this.yearOfPublication = yearOfPublication;
        this.author = author;
        this.title = title;
        this.access = true;
    }
}