package org.example.libraryapp.errorHandler

data class ApiError (
    val code: Int,
    val type: String,
    val description: String
)