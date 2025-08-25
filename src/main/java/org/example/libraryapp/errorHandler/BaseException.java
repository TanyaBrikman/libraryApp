package org.example.libraryapp.errorHandler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
abstract class BaseException extends RuntimeException {
    HttpStatus httpStatus;
    ApiError apiError;

    public BaseException(HttpStatus httpStatus, ApiError apiError) {
        this.httpStatus = httpStatus;
        this.apiError = apiError;
    }
}