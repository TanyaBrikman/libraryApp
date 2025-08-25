package org.example.libraryapp.errorHandler;


public record ApiError(Integer code,
                String type,
                String description) {
}