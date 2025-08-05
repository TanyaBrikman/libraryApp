package org.example.libraryapp.service

import org.example.libraryapp.dto.BookDTO
import org.example.libraryapp.entity.Book
import org.example.libraryapp.errorHandler.BookAccessDeniedException
import org.example.libraryapp.errorHandler.BookArgumentException
import org.example.libraryapp.errorHandler.BookNotFoundException
import org.example.libraryapp.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService(val bookRepository: BookRepository) {

    fun findAllBook(isAccessible: Boolean, page: Int, size: Int): Page<Book> {
        return bookRepository.findAll(PageRequest.of(page, size))
    }

    fun findBookById(id: Long): Book? {
        return bookRepository.findBookByIdOrIdNull(id) ?: throw BookNotFoundException(id)
    }

    @Transactional
    fun addBook(book: Book): Book {
        return bookRepository.save(book)
    }

    @Transactional
    fun updateBook(id: Long, bookDTO: BookDTO) {
        val book = bookRepository.findBookByIdOrIdNull(id) ?: throw BookNotFoundException(id)

        if (!book.access) {
            throw BookAccessDeniedException(id)
        }

        bookDTO.title?.also {
            if (bookDTO.title.isNotBlank()) {
                book.title = bookDTO.title
            } else {
                throw BookArgumentException("Enter title")
            }
        }

        bookDTO.author?.also {
            if (bookDTO.author.isNotBlank()) {
                book.author = bookDTO.author
            } else {
                throw BookArgumentException("Enter author")
            }
        }

        bookDTO.yearOfPublication?.also { book.yearOfPublication = it }
    }

    @Transactional
    fun deleteBook(id: Long) {
        val book = bookRepository.findBookByIdOrIdNull(id) ?: throw BookNotFoundException(id)
        book.access = false
    }

    fun searchBooks(title: String, page: Int, size: Int): Page<Book> {
        return bookRepository.findByTitleStartingWithIgnoreCase(title, PageRequest.of(page, size))
    }
}