package org.example.libraryapp.service

import org.assertj.core.api.Assertions.assertThat
import org.example.libraryapp.entity.Book
import org.example.libraryapp.repository.BookRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional

@ExtendWith(SpringExtension::class)
@SpringBootTest
@ActiveProfiles("test")
class BookServiceIntegrationTest {

    @Autowired
    private lateinit var bookRepository: BookRepository

    @Autowired
    private lateinit var bookService: BookService

    @Test
    @Transactional
    fun `Add books - ok`() {

        val newBook = Book(id = null, "Title", "Author", 1800, "123-45-12345-10-8", true)

        val savedBook = bookService.addBook(newBook)

        assertThat(savedBook.title).isEqualTo("Title")
        assertThat(savedBook.yearOfPublication).isGreaterThanOrEqualTo(1800)

        val book = savedBook.id?.let { bookRepository.findByIdOrNull(it) }

        assertThat(book).isNotNull
        assertThat(book).isEqualTo(savedBook)
    }
}