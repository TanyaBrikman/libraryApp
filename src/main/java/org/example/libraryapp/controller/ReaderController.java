package org.example.libraryapp.controller;

import jakarta.validation.Valid;
import org.example.libraryapp.dto.readerDTO.CreateReaderDTO;
import org.example.libraryapp.dto.readerDTO.UpdateReaderDTO;
import org.example.libraryapp.entity.Reader;
import org.example.libraryapp.service.ReaderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/readers")
@ResponseBody
@Controller
public class ReaderController {

    private final ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping()
    public List<Reader> findAllReaders() {
        return readerService.findAllReaders();
    }

    @GetMapping("/{id}")
    public Reader findReaderById(@PathVariable Long id) {
        return readerService.findReaderById(id);
    }

    @PostMapping
    public Reader createReader(@RequestBody
                            @Valid CreateReaderDTO createReaderDTO) {
        return readerService.createReader(createReaderDTO);
    }

    @PutMapping("/{id}")
    public void updateReader(@PathVariable Long id,
                             @RequestBody
                             @Valid UpdateReaderDTO updateReaderDTO) {
        readerService.updateReader(id, updateReaderDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteReader(@PathVariable Long id) {
        readerService.deleteReader(id);
    }
}