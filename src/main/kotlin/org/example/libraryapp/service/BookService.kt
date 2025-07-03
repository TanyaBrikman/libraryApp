package org.example.libraryapp.service

import org.example.libraryapp.dto.BookDTO
import org.example.libraryapp.entity.Book
import org.example.libraryapp.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class BookService(val bookRepository: BookRepository) {

    fun findAllBook(isAccessible: Boolean, page: Int, size: Int): Page<Book> {
        val pageable: Pageable = PageRequest.of(page, size)
        return bookRepository.findAll(pageable)
    }

    fun findBookById(id: Long): Book? {
        return bookRepository.findBookByIdOrIdNull(id)
    }

    fun addBook(book: Book): Book {
        return bookRepository.save(book)
    }

    @Transactional
    fun updateBook(id: Long, bookDTO: BookDTO) {
        val book = bookRepository.findBookByIdOrIdNull(id) ?: throw IllegalArgumentException("Book $id not found")
        bookDTO.title?.let { book.title = it }
        bookDTO.author?.let { book.author = it }
        bookDTO.yearOfPublication.let { book.yearOfPublication = it }

        bookRepository.save(book)
    }

    @Transactional
    fun deleteBook(id: Long) {
        val book = bookRepository.findBookByIdOrIdNull(id) ?: throw IllegalArgumentException("Book $id not found")
        book.access = false
        bookRepository.save(book)
    }

    fun searchBooks(title: String, page: Int, size: Int): Page<Book> {
        val pageable: Pageable = PageRequest.of(page, size)
        return bookRepository.findByTitleStartingWithIgnorCase(title, pageable)
    }


}