package org.example.libraryapp.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
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
@Tag(
    name = "Books",
    description = "All methods for working with books"
)
class BookController(
    val bookService: BookService
) {

    @GetMapping()
    @Operation(summary = "Get all books")
    fun findAllBooks(
        @Parameter(description = "Filter page", required = true)
        @RequestParam(required = true, defaultValue = "0") page: Int,
        @Parameter(description = "Filter size", required = true)
        @RequestParam(required = true, defaultValue = "10") size: Int,
        @Parameter(description = "Book access", required = true)
        @RequestParam(required = true, defaultValue = "true") isAccessible: Boolean
    ): Page<Book> {
        return bookService.findAllBook(page = page, size = size, isAccessible = isAccessible)
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a book by id")
    fun findBookById(
        @Parameter(description = "Book id")
        @PathVariable id: Long
    ): ResponseEntity<Book> {
        return bookService.findBookById(id)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()
    }

    @GetMapping("/search")
    @Operation(summary = "Search for a book by title")
    fun searchBooks(
        @Parameter(description = "Title book", required = true)
        @RequestParam title: String,
        @Parameter(description = "Filter page", required = true)
        @RequestParam(defaultValue = "0") page: Int,
        @Parameter(description = "Filter size", required = true)
        @RequestParam(defaultValue = "10") size: Int,
    ): Page<Book> {
        return bookService.searchBooks(title = title, page = page, size = size)
    }

    @PostMapping()
    @Operation(summary = "Create a book")
    fun addBook(@RequestBody book: Book): Book {
        return bookService.addBook(book)
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a book")
    fun updateBook(
        @Parameter(description = "Book id")
        @PathVariable id: Long,
        @RequestBody
        @Valid bookDTO: BookDTO
    ) {
        bookService.updateBook(id, bookDTO)
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book")
    fun deleteBook(
        @Parameter(description = "Book id")
        @PathVariable id: Long
    ) {
        bookService.deleteBook(id)
    }
}