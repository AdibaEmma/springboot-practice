package com.aweperi.springbootpractice.controller;

import com.aweperi.springbootpractice.exceptions.UserRegistrationException;
import com.aweperi.springbootpractice.service.UserRegistrationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/v1/registration")
@AllArgsConstructor
@Slf4j
public class UserRegistrationController {
    private final UserRegistrationService registrationService;

    @PostMapping("")
    public String register(@RequestBody UserRegistrationRequest request) {
        try {
            return registrationService.register(request);
        } catch (UserRegistrationException ex) {
            var cause = ex.getCause();
            log.error(cause.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, cause.getMessage(), ex);
        }
    }
}
