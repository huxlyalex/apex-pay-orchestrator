package com.apexpay.transactionservice.controller;

import com.apexpay.transactionservice.dto.TransactionRequest;
import com.apexpay.transactionservice.dto.TransactionResponse;
import com.apexpay.transactionservice.exception.DuplicateIdempotencyKeyException;
import com.apexpay.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/createTransaction")
    public ResponseEntity<TransactionResponse> createTransaction(@RequestHeader("X-Idempotency-Key") String idemKey,
                                                                 @RequestBody TransactionRequest transactionRequest) {
        try {
            TransactionResponse transactionResponse = transactionService.createTransaction(idemKey, transactionRequest);
            return new ResponseEntity<>(transactionResponse, HttpStatus.CREATED);
        } catch (DuplicateIdempotencyKeyException e) {
            // The global exception handler will catch this and return a 409 Conflict.
            // This catch block is for clarity, but the response is handled by the @ControllerAdvice.
            throw e;
        }
    }

    @GetMapping("/getTransactionStatus/{id}")
    public String getTransactionStatus(@PathVariable("id") String idemKey) {

        return  transactionService.getTransactionStatus(idemKey);

    }

    @GetMapping("/getHistory")
    public ResponseEntity<Page<TransactionResponse>> getHistory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy) {

        Page<TransactionResponse> history = transactionService.getHistory(page, size, sortBy);
        return new ResponseEntity<>(history,HttpStatus.OK);
    }
}
