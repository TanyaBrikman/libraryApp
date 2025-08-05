package org.example.libraryapp.errorHandler

import org.springframework.http.HttpStatus

class BookNotFoundException(userId: Long) : BaseException(
    httpStatus = HttpStatus.NOT_FOUND,
    apiError =  ApiError(
        404,
        "NOT_FOUND",
        "Book with id $userId not found",
        )
)