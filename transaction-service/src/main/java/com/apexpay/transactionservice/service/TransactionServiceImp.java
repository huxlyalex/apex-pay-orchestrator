package com.apexpay.transactionservice.service;

import com.apexpay.transactionservice.dto.TransactionRequest;
import com.apexpay.transactionservice.dto.TransactionResponse;
import com.apexpay.transactionservice.entity.Transaction;
import com.apexpay.transactionservice.exception.DuplicateIdempotencyKeyException;
import com.apexpay.transactionservice.mapper.TransactionMapper;
import com.apexpay.transactionservice.repository.TransactionRepository;

import jakarta.el.ELException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TransactionServiceImp implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;


    @Override
    public TransactionResponse createTransaction(String idemKey, TransactionRequest Transactionrequest) {

       Optional<Transaction>value =  transactionRepository.findByIdempotencyKey(idemKey);
       if(value.isPresent()) {
          throw new DuplicateIdempotencyKeyException("Idempotency key already exist");
       }
//       transactionMapper.toEntity(Transactionrequest,idemKey);
       return transactionMapper.toSuccessResponse(transactionRepository.save(transactionMapper.toEntity(Transactionrequest,idemKey)));


    }

    @Override
    public String getTransactionStatus(String idemKey) {
        if(!(transactionRepository.findByIdempotencyKey(idemKey).isPresent()))
        {
            return "Transaction is not present";
        }
        else {
            return transactionRepository.findByIdempotencyKey(idemKey).get().getStatus();
        }
    }

    @Override
    public Page<TransactionResponse> getHistory(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        Page<Transaction> data = null;
        try {
            data = transactionRepository.findAll(pageable);
        } catch (Exception e) {
            throw new com.apexpay.transactionservice.exception.TransactionFetchException("Failed to retrieve transaction history", e);
        }

//        data.map(transactionMapper::toSuccessResponse);

        return data.map(transaction ->  transactionMapper.toSuccessResponse(transaction));

//        return null;

    }


}
