package com.apexpay.transactionservice.exception;

public class TransactionFetchException extends RuntimeException {

    public TransactionFetchException(String message) {
        super(message);
    }

    public TransactionFetchException(String message, Throwable cause) {
        super(message, cause);
    }
}
