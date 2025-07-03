package org.example.libraryapp.repository

import org.example.libraryapp.entity.Book
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository: JpaRepository<Book, Long> {
    fun findBookByIdOrIdNull(id: Long): Book?
    fun findByTitleStartingWithIgnorCase(title: String, pageable: Pageable): Page<Book>
}