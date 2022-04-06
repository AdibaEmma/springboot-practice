package com.aweperi.springbootpractice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserRegistrationException extends RuntimeException{
    public UserRegistrationException(Throwable cause) {
        super(cause);
    }
}
