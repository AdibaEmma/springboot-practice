package com.aweperi.springbootpractice.exceptions;

public class InvalidEmailException extends Exception {
    public InvalidEmailException() {
        super("email is not valid!.");
    }
    public InvalidEmailException(String message) {
        super(message);
    }
}
