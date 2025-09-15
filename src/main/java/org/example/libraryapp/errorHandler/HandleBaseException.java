package org.example.libraryapp.errorHandler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
class HandleBaseException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public ApiError handlerBaseException(BaseException ex) {
        return ex.getApiError();
    }
}