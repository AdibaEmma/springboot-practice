package com.aweperi.springbootpractice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class DuplicateEmailException extends Exception {
    public DuplicateEmailException() {
        super("email already taken");
    }
    public DuplicateEmailException(String message) {
        super(message);
    }
}
