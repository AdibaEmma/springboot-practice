package com.aweperi.springbootpractice.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegistrationRequest {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
}
