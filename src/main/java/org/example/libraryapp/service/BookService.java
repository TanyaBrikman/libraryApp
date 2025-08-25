package org.example.libraryapp.service;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.libraryapp.dto.BookDTO;
import org.example.libraryapp.entity.Book;
import org.example.libraryapp.errorHandler.BookAccessDeniedException;
import org.example.libraryapp.errorHandler.BookArgumentException;
import org.example.libraryapp.errorHandler.BookNotFoundException;
import org.example.libraryapp.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    public Page<Book> findAllBook(Integer page, Integer size) {
        return bookRepository.findAll(PageRequest.of(page, size));
    }

    public Book findBookById(Long id) {
        if (id != null) {
            return bookRepository.findBookById(id);

        } else {
            throw new BookNotFoundException(id);
        }
    }

    @Transactional
    public Book addBook(@Valid Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public void updateBook(Long id, BookDTO bookDTO) {
        if (id != null) {
            Book book = bookRepository.findBookById(id);
            if (!book.getAccess()) {
                throw new BookAccessDeniedException(id);
            }

            if (bookDTO.title() != null) {
                if (!(bookDTO.title().trim().isEmpty())) {
                    book.setTitle(bookDTO.title());
                } else {
                    throw new BookArgumentException("Enter title");
                }

                if (bookDTO.author() != null) {
                    if (!(bookDTO.author().trim().isEmpty())) {
                        book.setAuthor(bookDTO.author());
                    } else {
                        throw new BookArgumentException("Enter author");
                    }
                }
            }

            if (bookDTO.yearOfPublication() != null) {
                book.setYearOfPublication(bookDTO.yearOfPublication());
            }
        } else {
            throw new BookNotFoundException(id);
        }
    }

    @Transactional
    public void deleteBook(Long id) {
        if (id != null) {
            Book book = bookRepository.findBookById(id);
            book.setAccess(false);
        }
        throw new BookNotFoundException(id);
    }

    public Page<Book> searchBooks(String title, Integer page, Integer size) {
        return bookRepository.findByTitleStartingWithIgnoreCase(title, PageRequest.of(page, size));
    }
}