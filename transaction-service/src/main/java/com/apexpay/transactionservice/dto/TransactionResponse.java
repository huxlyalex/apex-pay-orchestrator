package com.apexpay.transactionservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private Long id;
    private String idempotencyKey;
    private Double amount;
    private String currency;
    private String status;
    private LocalDateTime createdAt;
    private String message;

    // We hide gatewayReference unless the user specifically needs it
    // This keeps our internal integration details private.
}
