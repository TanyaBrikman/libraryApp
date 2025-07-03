package org.example.libraryapp.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

@Entity
@Table(name = "books")
class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "author", nullable = false)
    var author: String,

    @Column(name = "year_of_publication", nullable = false)
    var yearOfPublication: Int,

    @Column(name = "isbn", nullable = false, unique = true)
    @Size(min = 17, max = 17)
    @Pattern(regexp = "^\\d{3}-\\d{2}-\\d{5}-\\d{2}-\\d$", message = "Формат: XXX-XX-XXXXX-XX-X, где X это число")
    val isbn: String,

    @Column(name = "access", nullable = false)
    var access: Boolean = true
)
