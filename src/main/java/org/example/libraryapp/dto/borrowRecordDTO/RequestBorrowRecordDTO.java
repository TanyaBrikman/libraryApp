package org.example.libraryapp.dto.borrowRecordDTO;

import jakarta.validation.constraints.NotBlank;

public record RequestBorrowRecordDTO(
        @NotBlank
        Long bookId,
        @NotBlank
        Long readerId
) {
}