package com.apexpay.transactionservice.exception;

public class DuplicateIdempotencyKeyException extends  RuntimeException  {

    public  DuplicateIdempotencyKeyException(String message){
        super(message);

    }
}
