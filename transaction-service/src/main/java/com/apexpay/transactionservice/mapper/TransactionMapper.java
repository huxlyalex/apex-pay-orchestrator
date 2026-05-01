package com.apexpay.transactionservice.mapper;

import com.apexpay.transactionservice.dto.TransactionRequest;
import com.apexpay.transactionservice.dto.TransactionResponse;
import com.apexpay.transactionservice.entity.Transaction;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TransactionMapper {

    public Transaction toEntity(TransactionRequest request, String idempotencyKey) {
        if (request == null) return null;

        return Transaction.builder()
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .idempotencyKey(idempotencyKey)
                .status("PENDING") // Initial status
                .createdAt(LocalDateTime.now())
                .build();
    }

    public TransactionResponse toSuccessResponse(Transaction entity) {
        if (entity == null) return null;

        return TransactionResponse.builder()
                .id(entity.getId())
                .amount(entity.getAmount())
                .currency(entity.getCurrency())
                .status(entity.getStatus())
                .idempotencyKey(entity.getIdempotencyKey())
                .createdAt(entity.getCreatedAt())
                .message("Success")
                .build();
    }

    public TransactionResponse toFailTransactionResponse() {
       return TransactionResponse.builder()
               .message("failed").build();
    }


}