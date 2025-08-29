package org.example.libraryapp.dto.bookDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record CreateBookDTO(
    @Schema(description = "Book title")
    @Size(max = 200, message = "Maximum number of characters 200")
    String title,
    @Size(max = 100, message = "Maximum number of characters 100")
    @Schema(description = "Author of the book")
    String author,
    @Schema(description = "Date the book")
    @Min(value = 1800, message = "The year cannot be earlier than 1800")
    @Max(value = 2025, message = "The year cannot be the future")
    Integer yearOfPublication,
    @Schema(description = "Book isbn")
    String isbn
) {}