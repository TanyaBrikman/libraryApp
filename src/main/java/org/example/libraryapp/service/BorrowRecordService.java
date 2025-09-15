package org.example.libraryapp.service;

import org.example.libraryapp.dto.borrowRecordDTO.RequestBorrowRecordDTO;
import org.example.libraryapp.entity.Book;
import org.example.libraryapp.entity.BorrowRecord;
import org.example.libraryapp.entity.Reader;
import org.example.libraryapp.errorHandler.BookAlreadyBorrowedException;
import org.example.libraryapp.errorHandler.BookNotFoundException;
import org.example.libraryapp.errorHandler.BorrowRecordNotFoundException;
import org.example.libraryapp.errorHandler.ReaderNotFoundException;
import org.example.libraryapp.repository.BookRepository;
import org.example.libraryapp.repository.BorrowRecordRepository;
import org.example.libraryapp.repository.ReaderRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class BorrowRecordService {

    private final BorrowRecordRepository borrowRecordRepository;

    private final BookRepository bookRepository;

    private final ReaderRepository readerRepository;

    BorrowRecordService(
            BorrowRecordRepository borrowRecordRepository,
            BookRepository bookRepository,
            ReaderRepository readerRepository
    ) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
    }

    public List<BorrowRecord> getAllBorrowRecord() {
        return borrowRecordRepository.findAll();
    }

    public BorrowRecord getBorrowRecordServiceById(Long recordId) {
        return borrowRecordRepository.getBorrowRecordById(recordId)
                .orElseThrow(() -> new BorrowRecordNotFoundException(recordId));
    }

    public BorrowRecord createBorrowRecord(RequestBorrowRecordDTO requestBorrowRecordDTO) {
        Book book = bookRepository.getBookById(requestBorrowRecordDTO.bookId())
                .orElseThrow(() -> new BookNotFoundException(requestBorrowRecordDTO.bookId()));
        Reader reader = readerRepository.getReaderById(requestBorrowRecordDTO.readerId())
                .orElseThrow(() -> new ReaderNotFoundException(requestBorrowRecordDTO.readerId()));

        if (book.getAccess()) {
            book.setAccess(false);
            bookRepository.save(book);
            return borrowRecordRepository.save(new BorrowRecord(book, reader));
        } else {
            throw new BookAlreadyBorrowedException(book.getId());
        }
    }

    public BorrowRecord returnBorrowRecord(Long recordId) {
        BorrowRecord borrowRecord = borrowRecordRepository.getBorrowRecordById(recordId)
                .orElseThrow(() -> new BorrowRecordNotFoundException(recordId));

        if (borrowRecord.getReturnDate() == null) {
            borrowRecord.setReturnDate(Instant.now());
            borrowRecord.getBook().setAccess(true);
            bookRepository.save(borrowRecord.getBook());
            return borrowRecordRepository.save(borrowRecord);
        } else {
            throw new BorrowRecordNotFoundException(recordId);
        }
    }
}