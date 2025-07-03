package org.example.libraryapp.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Size

data class BookDTO(

    @field:Size(max = 200, message = "максимальное количество символов 200")
    var title: String?,
    @field:Size(max = 10, message = "Максимальное количество символов 100")
    var author: String?,
    @field:Min(1800, message = "Год не может быть раньше 1800")
    @field:Max(2025, message = "Год не может быть будущим")
    var yearOfPublication: Int
)