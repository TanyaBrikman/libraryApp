package org.example.libraryapp.errorHandler

import org.springframework.http.HttpStatus

class BookAccessDeniedException(id: Long) : BaseException(
    HttpStatus.BAD_REQUEST,
    ApiError(
        400,
        "Bad Request",
        "Book with id $id not accessible for updates"
    )
)