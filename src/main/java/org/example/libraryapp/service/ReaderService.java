package org.example.libraryapp.service;

import org.example.libraryapp.dto.readerDTO.CreateReaderDTO;
import org.example.libraryapp.dto.readerDTO.UpdateReaderDTO;
import org.example.libraryapp.entity.Reader;
import org.example.libraryapp.errorHandler.ArgumentException;
import org.example.libraryapp.errorHandler.ReaderNotFoundException;
import org.example.libraryapp.repository.ReaderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ReaderService {

    private final ReaderRepository readerRepository;

    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public List<Reader> findAllReaders() {
        return readerRepository.findAll();
    }

    public Reader findReaderById(Long id) {
        return readerRepository.findReaderById(id);
    }

    @Transactional
    public Reader createReader(CreateReaderDTO createReaderDTO) {
        Reader existingReader = readerRepository.findReaderByEmail(createReaderDTO.email());
        if (existingReader == null) {
            return readerRepository.save(new Reader(createReaderDTO.name(), createReaderDTO.email()));
        } else {
            throw new ArgumentException("Email " + createReaderDTO.email() + " already exists");
        }
    }

    @Transactional
    public void updateReader(Long id, UpdateReaderDTO readerDTO) {
        Reader reader = readerRepository.findReaderById(id);

        Reader existingReader = readerRepository.findReaderByEmail(readerDTO.email());

        if (reader != null) {
            if (existingReader == null) {
                if (readerDTO.email() != null) {
                    if (!(readerDTO.email().trim().isEmpty())) {
                        reader.setEmail(readerDTO.email());
                    } else {
                        throw new ArgumentException("Enter email");
                    }
                }
            } else {
                throw new ArgumentException("Email already exists");
            }
            if (readerDTO.name() != null) {
                if (!(readerDTO.name().trim().isEmpty())) {
                    reader.setName(readerDTO.name());
                } else {
                    throw new ArgumentException("Enter name");
                }
            }
        } else {
            throw new ReaderNotFoundException(id);
        }
    }

    @Transactional
    public void deleteReader(@PathVariable Long id) {
        readerRepository.deleteById(id);
    }
}