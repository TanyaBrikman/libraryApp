package org.example.libraryapp.repository;

import org.example.libraryapp.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    Optional <BorrowRecord> getBorrowRecordById(Long id);
}
