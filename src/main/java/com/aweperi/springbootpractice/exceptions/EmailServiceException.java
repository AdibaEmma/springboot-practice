package com.aweperi.springbootpractice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED)
public class EmailServiceException extends RuntimeException{
    public EmailServiceException() {
        super("failed to send email");
    }
    public EmailServiceException(String message) {
        super(message);
    }
}
