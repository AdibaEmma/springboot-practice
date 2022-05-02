package com.aweperi.springbootpractice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException() {
        super("role not found");
    }
    public RoleNotFoundException(String message) {
        super(message);
    }
}
