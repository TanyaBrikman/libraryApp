package org.example.libraryapp.errorHandler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ReaderNotFoundException extends BaseException {

    final Long id;

    public ReaderNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND,
                new ApiError(404, "NOT_FOUND", "Reader with id " + id + " not found"));
        this.id = id;
    }
}