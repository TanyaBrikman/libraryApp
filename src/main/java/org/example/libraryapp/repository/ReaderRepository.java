package org.example.libraryapp.repository;

import org.example.libraryapp.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
    Optional<Reader> getReaderById(Long id);
    Reader findReaderByEmail(String email);
}