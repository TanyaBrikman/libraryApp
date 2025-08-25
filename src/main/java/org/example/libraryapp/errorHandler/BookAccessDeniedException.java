package org.example.libraryapp.errorHandler;

import org.springframework.http.HttpStatus;

public class BookAccessDeniedException extends BaseException {

    Long id;

    public BookAccessDeniedException(Long id) {
        super(HttpStatus.BAD_REQUEST, new ApiError(
                400,
                "Bad Request",
                "Book with id" + id + " not accessible for updates"));
        this.id = id;
    }
}