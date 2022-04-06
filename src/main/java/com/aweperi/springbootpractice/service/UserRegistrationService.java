package com.aweperi.springbootpractice.service;

import com.aweperi.springbootpractice.controller.UserRegistrationRequest;
import com.aweperi.springbootpractice.exceptions.InvalidEmailException;
import com.aweperi.springbootpractice.exceptions.UserRegistrationException;
import com.aweperi.springbootpractice.model.User;
import com.aweperi.springbootpractice.model.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRegistrationService {
    private final EmailValidator emailValidator;
    private final UserService userService;

    public String register(UserRegistrationRequest request) {
        var isValidEmail = emailValidator.test(request.getEmail());

        if(!isValidEmail) throw new UserRegistrationException( new InvalidEmailException());
        return userService.signUpUser(
                new User(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        UserRole.USER
                )
        );
    }
}
