package org.example.libraryapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;


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
}