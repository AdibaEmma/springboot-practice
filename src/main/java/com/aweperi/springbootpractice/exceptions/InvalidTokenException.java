package com.aweperi.springbootpractice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException() {
        super("invalid token");
    }
    public InvalidTokenException(String message) {
        super(message);
    }
}
