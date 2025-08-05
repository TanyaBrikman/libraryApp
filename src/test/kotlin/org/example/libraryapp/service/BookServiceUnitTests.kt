package org.example.libraryapp.service

import org.assertj.core.api.Assertions.assertThat
import org.example.libraryapp.dto.BookDTO
import org.example.libraryapp.entity.Book
import org.example.libraryapp.errorHandler.BookAccessDeniedException
import org.example.libraryapp.errorHandler.BookArgumentException
import org.example.libraryapp.errorHandler.BookNotFoundException
import org.example.libraryapp.repository.BookRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest

@ExtendWith(MockitoExtension::class)
class BookServiceUnitTests {

    @Mock
    private lateinit var bookRepository: BookRepository

    @InjectMocks
    private lateinit var bookService: BookService

    @Test
    fun `GET book by id`() {

        //подготовка тестовых данных
        val bookId = 1L

        val testBook = Book(
            bookId,
            "Title",
            "Author",
            1880,
            "123-45-12345-12-1",
            true
        )

        //Действие
        `when`(bookRepository.findBookByIdOrIdNull(bookId)).thenReturn(testBook)

        //Вызов тестируемого метода и проверка результата
        val result = bookService.findBookById(bookId)
        assertThat(result).isNotNull()
        assertThat(result?.title).isEqualTo("Title")
        assertThat(result?.author).isEqualTo("Author")

        verify(bookRepository).findBookByIdOrIdNull(bookId)
    }

    @Test
    fun `GET book by id should throw BookArgumentException when not found`() {
        //Подготовка
        val bookId = 1L
        `when`(bookRepository.findBookByIdOrIdNull(bookId)).thenThrow(BookNotFoundException(bookId))

        //Действие и проверка
        assertThrows<BookNotFoundException> {
            bookService.findBookById(bookId)
        }

        verify(bookRepository).findBookByIdOrIdNull(bookId)
    }

    @Test
    fun `GET book by id should throw BookAccessDeniedException when not found`() {
        val bookId = 1L
        `when` (bookRepository.findBookByIdOrIdNull(bookId)).thenThrow(BookAccessDeniedException(bookId))

        assertThrows<BookAccessDeniedException> {
            bookService.findBookById(bookId)
        }

        verify(bookRepository).findBookByIdOrIdNull(bookId)
    }

    @Test
    fun `PUT book by id - ok`() {

        val bookId = 1L

        val testBook = Book(
            bookId,
            "Title",
            "Author",
            1880,
            "123-45-12345-12-1",
            true
        )

        val updateBook = BookDTO(
            bookId,
            "Title1",
            "Author1",
            true,
            1722
        )

        `when`(bookRepository.findBookByIdOrIdNull(bookId)).thenReturn(testBook)

        val result = bookService.updateBook(bookId, updateBook)

        assertThat(result).isNotNull()
        assertThat(testBook.id).isEqualTo(bookId)
        assertThat(updateBook.access).isTrue
        assertThat(updateBook.title).isNotBlank()
        assertThat(updateBook.author).isNotBlank()

        verify(bookRepository).findBookByIdOrIdNull(bookId)
    }

    @Test
    fun `PUT book update not possible`() {

        val bookId = 1L

        val updateBook = BookDTO(
            bookId,
            " ",
            "  ",
            false,
            null
        )

        `when`(bookRepository.findBookByIdOrIdNull(bookId)).thenThrow(BookArgumentException("update not possible"))

        assertThrows<BookArgumentException> {
            bookService.updateBook(bookId, updateBook)
        }

        verify(bookRepository).findBookByIdOrIdNull(bookId)
    }

    @Test

    fun `POST book search - ok`() {

        val bookTitle = "Title"
        val bookId = 1L
        val page = 1
        val size = 1

        val testBook = Book(
            bookId,
            "Title",
            "Author",
            1880,
            "123-45-12345-12-1",
            true
        )

        val bookPage: Page<Book> = PageImpl(listOf(testBook), PageRequest.of(page, size), 1)

        `when`(bookRepository.findByTitleStartingWithIgnoreCase(bookTitle, PageRequest.of(page, size))).thenReturn(bookPage)

        val result = bookService.searchBooks(bookTitle, page, size)

        assertThat(result).isNotNull

        assertThat(result.content).contains(testBook)

        verify(bookRepository).findByTitleStartingWithIgnoreCase(bookTitle, PageRequest.of(page, size))
    }

    @Test
    fun `DELETE by book id - ok`() {
        val bookId = 1L

        val testBook = Book(
            bookId,
            "Title",
            "Author",
            1880,
            "123-45-12345-12-1",
            true
        )

        val deleteBook = Book(
            bookId,
            "Title",
            "Author",
            1880,
            "123-45-12345-12-9",
            false
        )

        `when`(bookRepository.findBookByIdOrIdNull(bookId)).thenReturn(testBook)

        assertThat(deleteBook.access).isFalse()

        verify(bookRepository).findBookByIdOrIdNull(bookId)
    }



}