package org.example.libraryapp.errorHandler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BookNotFoundException extends BaseException {

    final Long id;

    public BookNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND,
                new ApiError(404, "Not found", "Book with id " + id + " not found"));
        this.id = id;
    }
}