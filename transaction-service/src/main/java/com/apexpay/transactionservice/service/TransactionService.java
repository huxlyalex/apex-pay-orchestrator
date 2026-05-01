package com.apexpay.transactionservice.service;


import com.apexpay.transactionservice.dto.TransactionRequest;
import com.apexpay.transactionservice.dto.TransactionResponse;
import org.springframework.data.domain.Page;


public interface TransactionService {

    public TransactionResponse createTransaction(String idemKey, TransactionRequest Transactionrequest);
    public String getTransactionStatus(String idemKey);

     public Page<TransactionResponse> getHistory(int page, int size, String sortBy);
}
