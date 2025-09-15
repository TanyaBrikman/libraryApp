package org.example.libraryapp.errorHandler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BorrowRecordNotFoundException extends BaseException {

    final Long id;

    public BorrowRecordNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND,
                new ApiError(404, "Not found", "Borrow record with id " + id + " not found"));
        this.id = id;
    }
}