package org.example.libraryapp.errorHandler

import org.springframework.http.HttpStatus

class BookArgumentException(message: String) : BaseException(
    httpStatus = HttpStatus.BAD_REQUEST,
    apiError = ApiError(
        400,
        "Bad Request",
        message
    )
)