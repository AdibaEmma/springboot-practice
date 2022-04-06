package com.aweperi.springbootpractice.controller;

import com.aweperi.springbootpractice.exceptions.UserRegistrationException;
import com.aweperi.springbootpractice.service.UserRegistrationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/registration")
@AllArgsConstructor
@Slf4j
public class UserRegistrationController {
    private final UserRegistrationService registrationService;

    @PostMapping("")
    public ResponseEntity register(@RequestBody UserRegistrationRequest request) {
        try {
            Map<String, Object> responseBody = new LinkedHashMap<>();
            responseBody.put("status", HttpServletResponse.SC_CREATED);
            responseBody.put("message", "success");
            responseBody.put("body", registrationService.register(request));

            return ResponseEntity.status(HttpStatus.CREATED)

                    .body(responseBody);
        } catch (UserRegistrationException ex) {
            var cause = ex.getCause();
            log.error(cause.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, cause.getMessage(), ex);
        }
    }
}
