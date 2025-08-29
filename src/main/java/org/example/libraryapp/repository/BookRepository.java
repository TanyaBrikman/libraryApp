package org.example.libraryapp.repository;

import org.example.libraryapp.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findByTitleStartingWithIgnoreCase(String title, Pageable pageable);

    Optional<Book> findBookById(Long id);
}