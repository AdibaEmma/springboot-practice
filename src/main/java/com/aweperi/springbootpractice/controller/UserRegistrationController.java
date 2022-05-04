package com.aweperi.springbootpractice.controller;

import com.aweperi.springbootpractice.exceptions.UserAccountException;
import com.aweperi.springbootpractice.exceptions.UserRegistrationException;
import com.aweperi.springbootpractice.service.UserRegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("api/v1/signup")
@AllArgsConstructor
@Slf4j
public class UserRegistrationController {
    private final UserRegistrationService registrationService;

    LinkedHashMap<String, Object> responseBody;
    private static final String SUCCESS_MSG = "success";

    @Operation(summary = "Creates a user object")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Signup Successful",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request",
                    content = {@Content(mediaType = "application/json")}
            ),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error",
                    content = {@Content(mediaType = "application/json")}
            )
    })
    @PostMapping("")
    public ResponseEntity<LinkedHashMap<String, Object>> register(@RequestBody UserRegistrationRequest request) {
        try {
            responseBody.put(ResponseKeys.STATUS.toString(), HttpServletResponse.SC_CREATED);
            responseBody.put(ResponseKeys.MESSAGE.toString(), SUCCESS_MSG);
            responseBody.put(ResponseKeys.PAYLOAD.toString(), registrationService.register(request));

            log.info(String.valueOf(responseBody));
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(responseBody);
        } catch (UserRegistrationException ex) {
            var cause = ex.getCause();
            log.error(cause.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, cause.getMessage(), ex);
        }
    }

    @Operation(summary = "Confirms user token on email confirmation link")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202",
            description = "confirmed user signup",
            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
            description = "Bad request",
            content = {@Content(mediaType = "application/json")}        
            )
    })
    @GetMapping("confirm")
    public ResponseEntity<LinkedHashMap<String, Object>> confirm(@RequestParam("token") String token) {
        try {
            responseBody.put(ResponseKeys.STATUS.toString(), HttpServletResponse.SC_ACCEPTED);
            responseBody.put(ResponseKeys.MESSAGE.toString(), SUCCESS_MSG);
            responseBody.put(ResponseKeys.PAYLOAD.toString(), registrationService.confirmToken(token));

            log.info(String.valueOf(responseBody));
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(responseBody);
        } catch(UserAccountException ex) {
            var cause = ex.getCause();
            log.error(cause.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, cause.getMessage(),ex);
        }
    }
}
