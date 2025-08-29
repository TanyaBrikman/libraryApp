package org.example.libraryapp.repository;

import org.example.libraryapp.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
    Reader findReaderById(Long id);
    Reader findReaderByEmail(String email);
}