package org.example.libraryapp.errorHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
class HandleBaseException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiError> handlerBaseException(BaseException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getApiError());
    }
}