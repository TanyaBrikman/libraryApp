package org.example.libraryapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "readers")
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name не может быть пустым")
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    @NotBlank(message = "Email не может быть пустым")
    private String email;

    @Column(name = "date_of_registration", nullable = false)
    private Instant dateOfRegistration;

    public Reader(String name, String email) {
        this.name = name;
        this.email = email;
        this.dateOfRegistration = Instant.now();
    }
}