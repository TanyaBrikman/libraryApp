package org.example.libraryapp.dto


import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Size

@Schema(description = "Info of the book")
data class BookDTO(
    @Schema(description = "ID of the book")
    val id: Long,
    @Schema(description = "Book title")
    @field:Size(max = 200, message = "Maximum number of characters 200")
    val title: String?,
    @field:Size(max = 100, message = "Maximum number of characters 100")
    @Schema(description = "Author of the book")
    val author: String?,
    @Schema(description = "Book access")
    var access: Boolean,
    @Schema(description = "Date the book")
    @field:Min(1800, message = "The year cannot be earlier than 1800")
    @field:Max(2025, message = "The year cannot be the future")
    val yearOfPublication: Int?
)