package org.example.libraryapp.errorHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
public class BookNotFoundException extends BaseException {

    final Long id;

    public BookNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND,
                new ApiError(404, "NOT_FOUND", "Book with id " + id + " not found"));
        this.id = id;
    }
}