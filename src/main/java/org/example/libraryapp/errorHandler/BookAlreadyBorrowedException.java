package org.example.libraryapp.errorHandler;

import org.springframework.http.HttpStatus;

public class BookAlreadyBorrowedException extends BaseException {
    public BookAlreadyBorrowedException(Long bookId) {
        super(HttpStatus.NOT_FOUND,
                new ApiError(409, "Conflict", "Book with by id " + bookId + " unavailable"));
    }
}