package com.aweperi.springbootpractice.service;

import com.aweperi.springbootpractice.controller.UserRegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {
    public String register(UserRegistrationRequest request) {
        return "works";
    }
}
