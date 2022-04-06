package com.aweperi.springbootpractice.controller;

import com.aweperi.springbootpractice.service.UserRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/registration")
@AllArgsConstructor
public class UserRegistrationController {
    private final UserRegistrationService registrationService;

    public String register(@RequestBody UserRegistrationRequest request) {
        return registrationService.register(request);
    }
}
