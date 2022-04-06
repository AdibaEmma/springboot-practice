package com.aweperi.springbootpractice.exceptions;

public class DuplicateEmailException extends Exception {
    public DuplicateEmailException() {
        super("email already taken");
    }
    public DuplicateEmailException(String message) {
        super(message);
    }
}
