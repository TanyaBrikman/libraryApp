package org.example.libraryapp.controller

import jakarta.validation.Valid
import org.example.libraryapp.dto.BookDTO
import org.example.libraryapp.entity.Book
import org.example.libraryapp.service.BookService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("api/books")
@ResponseBody
class BookController(
    val bookService: BookService
) {

    @GetMapping()
    fun findAllBook(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
        @RequestParam(required = true, defaultValue = "true") isAccessible: Boolean
    ): Page<Book> {
        return bookService.findAllBook(page = page, size = size, isAccessible = isAccessible)
    }

    @GetMapping("/{id}")
    fun findBookById(@PathVariable id: Long): ResponseEntity<Book> {
        return bookService.findBookById(id)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()
    }

    @GetMapping("/search")
    fun searchBooks(
        @RequestParam title: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
    ): Page<Book> {
        return bookService.searchBooks(title = title, page = page, size = size)
    }

    @PostMapping()
    fun addBook(@RequestBody book: Book): Book {
        return bookService.addBook(book)
    }

    @PutMapping("/{id}")
    fun updateBook(
        @PathVariable id: Long,
        @RequestBody @Valid bookDTO: BookDTO
    ) {
        bookService.updateBook(id, bookDTO)
    }

    @DeleteMapping("/{id}")
    fun deleteBook(@PathVariable id: Long) {
        bookService.deleteBook(id)
    }
}

