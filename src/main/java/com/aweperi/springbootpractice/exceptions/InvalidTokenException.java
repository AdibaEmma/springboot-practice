package com.aweperi.springbootpractice.exceptions;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException() {
        super("token not found");
    }
    public InvalidTokenException(String message) {
        super(message);
    }
}
