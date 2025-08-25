package org.example.libraryapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.libraryapp.dto.BookDTO;
import org.example.libraryapp.entity.Book;
import org.example.libraryapp.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/books")
@ResponseBody
@RequiredArgsConstructor
@Tag(
        name = "Books",
        description = "All methods for working with books"
)
class BookController {

    private final BookService bookService;

    @GetMapping()
    @Operation(summary = "Get all books")
    public Page<Book> findAllBooks(
            @Parameter(description = "Filter page", required = true)
            @RequestParam(defaultValue = "0") Integer page,
            @Parameter(description = "Filter size", required = true)
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return bookService.findAllBook(page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a book by id")
    public Book findBookById(
            @Parameter(description = "Book id")
            @PathVariable Long id
    ) {
        return bookService.findBookById(id);
    }

    @GetMapping("/search")
    @Operation(summary = "Search for a book by title")
    public Page<Book> searchBooks(
            @Parameter(description = "Title book", required = true)
            @RequestParam String title,
            @Parameter(description = "Filter page", required = true)
            @RequestParam(defaultValue = "0") Integer page,
            @Parameter(description = "Filter size", required = true)
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return bookService.searchBooks(title, page, size);
    }

    @PostMapping()
    @Operation(summary = "Create a book")
    Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a book")
    public void updateBook(
            @Parameter(description = "Book id")
            @PathVariable Long id,
            @RequestBody
            @Valid BookDTO bookDTO
    ) {
        bookService.updateBook(id, bookDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book")
    public void deleteBook(
            @Parameter(description = "Book id")
            @PathVariable Long id
    ) {
        bookService.deleteBook(id);
    }
}