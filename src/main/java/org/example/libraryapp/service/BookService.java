package org.example.libraryapp.service;

import lombok.RequiredArgsConstructor;
import org.example.libraryapp.dto.bookDTO.CreateBookDTO;
import org.example.libraryapp.dto.bookDTO.UpdateBookDTO;
import org.example.libraryapp.entity.Book;
import org.example.libraryapp.errorHandler.ArgumentException;
import org.example.libraryapp.errorHandler.BookAccessDeniedException;
import org.example.libraryapp.errorHandler.BookNotFoundException;
import org.example.libraryapp.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    public Page<Book> findAllBook(
            Integer page,
            Integer size
    ) {
        return bookRepository.findAll(PageRequest.of(page, size));
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id) .orElseThrow(() -> new BookNotFoundException(id));

    }

    @Transactional
    public Book createBook(CreateBookDTO createBookDTO) {
        return bookRepository.save(new Book(
                createBookDTO.title(),
                createBookDTO.author(),
                createBookDTO.yearOfPublication(),
                createBookDTO.isbn()
                )
        );
    }

    @Transactional
    public void updateBook(
            Long id,
            UpdateBookDTO updateBookDTO
    ) {
        Book book = bookRepository.findBookById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        if (!book.getAccess()) {
            throw new BookAccessDeniedException(id);
        }

        if (updateBookDTO.title() != null) {
            if (!(updateBookDTO.title().trim().isEmpty())) {
                book.setTitle(updateBookDTO.title());
            } else {
                throw new ArgumentException("Enter title");
            }
            if (updateBookDTO.author() != null) {
                if (!(updateBookDTO.author().trim().isEmpty())) {
                    book.setAuthor(updateBookDTO.author());
                } else {
                    throw new ArgumentException("Enter author");
                }
            }
        }
        if (updateBookDTO.yearOfPublication() != null) {
            book.setYearOfPublication(updateBookDTO.yearOfPublication());
        }
    }

    @Transactional
    public void deleteBook(Long id) {
        Book book = bookRepository.findBookById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        book.setAccess(false);
    }

    public Page<Book> searchBooks(
            String title,
            Integer page,
            Integer size
    ) {
        return bookRepository.findByTitleStartingWithIgnoreCase(title, PageRequest.of(page, size));
    }
}