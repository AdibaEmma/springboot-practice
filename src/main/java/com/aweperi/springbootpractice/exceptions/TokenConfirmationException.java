package com.aweperi.springbootpractice.exceptions;

public class TokenConfirmationException extends RuntimeException{
    public TokenConfirmationException() {
        super("email already confirmed");
    }
    public TokenConfirmationException(String message) {
        super(message);
    }
}
