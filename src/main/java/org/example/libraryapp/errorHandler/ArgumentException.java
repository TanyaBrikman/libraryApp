package org.example.libraryapp.errorHandler;

import org.springframework.http.HttpStatus;

public class ArgumentException extends BaseException {

    String message;

    public ArgumentException(String message) {
        super(HttpStatus.BAD_REQUEST,
                new ApiError(400, "Bad Request", message));

        this.message = message;
    }
}