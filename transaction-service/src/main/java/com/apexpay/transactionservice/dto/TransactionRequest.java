package com.apexpay.transactionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    private Double amount;
    private String currency;
    private String description; // Optional: good for tracking
    // We don't include Idempotency Key here because it's in the Header!
}