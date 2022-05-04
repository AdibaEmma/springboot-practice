package com.aweperi.springbootpractice.errors;

import com.aweperi.springbootpractice.exceptions.InvalidEmailException;
import com.aweperi.springbootpractice.exceptions.RoleNotFoundException;
import com.aweperi.springbootpractice.exceptions.UserAccountException;
import com.aweperi.springbootpractice.exceptions.UserNotFoundException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @NonNull HttpHeaders headers, HttpStatus status, @NonNull WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage userNotFoundException(UserNotFoundException ex) {
        log.error(ex.getMessage());
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), "User Not Found Error", ex.getMessage());

    }

    @ExceptionHandler(value = InvalidEmailException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage InvalidEmailException(UserAccountException ex) {
        log.error(ex.getMessage());
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), "Invalid Email Error", ex.getCause().getMessage());
    }

    @ExceptionHandler(value = RoleNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage roleNotFoundException(UserAccountException ex) {
        log.error(ex.getMessage());
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), "Role Not Found Error", ex.getCause().getMessage());
    }

    @ExceptionHandler(value = ClassNotFoundException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage classNotFoundException(ClassNotFoundException ex) {
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                "An unknown error occurred",
                ex.getMessage());
    }
}
