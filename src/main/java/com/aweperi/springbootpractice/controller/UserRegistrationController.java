package com.aweperi.springbootpractice.controller;

import com.aweperi.springbootpractice.exceptions.UserAccountException;
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

    LinkedHashMap<String, Object> responseBody;
    private static final String SUCCESS_MSG = "success";

    @PostMapping("")
    public ResponseEntity<LinkedHashMap<String, Object>> register(@RequestBody UserRegistrationRequest request) {
        try {
            responseBody.put(ResponseKeys.STATUS.toString(), HttpServletResponse.SC_CREATED);
            responseBody.put(ResponseKeys.MESSAGE.toString(), SUCCESS_MSG);
            responseBody.put(ResponseKeys.PAYLOAD.toString(), registrationService.register(request));

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(responseBody);
        } catch (UserRegistrationException ex) {
            var cause = ex.getCause();
            log.error(cause.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, cause.getMessage(), ex);
        }
    }

    @GetMapping("confirm")
    public ResponseEntity<LinkedHashMap<String, Object>> confirm(@RequestParam("token") String token) {
        try {
            responseBody.put(ResponseKeys.STATUS.toString(), HttpServletResponse.SC_ACCEPTED);
            responseBody.put(ResponseKeys.MESSAGE.toString(), SUCCESS_MSG);
            responseBody.put(ResponseKeys.PAYLOAD.toString(), registrationService.confirmToken(token));

            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(responseBody);
        } catch(UserAccountException ex) {
            var cause = ex.getCause();
            log.error(cause.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, cause.getMessage(),ex);
        }

    }
}
