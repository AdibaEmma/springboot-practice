package com.aweperi.springbootpractice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserAccountException extends RuntimeException {
    public UserAccountException(Throwable cause) {
        super(cause);
    }
}
