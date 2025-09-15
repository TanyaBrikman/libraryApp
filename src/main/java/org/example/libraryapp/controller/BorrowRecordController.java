package org.example.libraryapp.controller;

import org.example.libraryapp.dto.borrowRecordDTO.RequestBorrowRecordDTO;
import org.example.libraryapp.entity.BorrowRecord;
import org.example.libraryapp.service.BorrowRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("api/borrow")
public class BorrowRecordController {

    private final BorrowRecordService borrowRecordService;

    public BorrowRecordController(BorrowRecordService borrowRecordService) {
        this.borrowRecordService = borrowRecordService;
    }

    @GetMapping
    public List<BorrowRecord> getAllBorrowRecord() {
        return borrowRecordService.getAllBorrowRecord();
    }

    @GetMapping("/{recordId}")
    public BorrowRecord getBorrowRecordById(@PathVariable Long recordId) {
        return borrowRecordService.getBorrowRecordServiceById(recordId);
    }

    @PostMapping
    public BorrowRecord createBorrowRecord(
            @RequestBody
            RequestBorrowRecordDTO requestBorrowRecordDTO) {
        return borrowRecordService.createBorrowRecord(requestBorrowRecordDTO);
    }

    @PutMapping("{recordId}/return")
    public BorrowRecord returnBorrowRecord(@PathVariable Long recordId) {
        return borrowRecordService.returnBorrowRecord(recordId);
    }
}