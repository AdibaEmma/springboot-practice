package com.aweperi.springbootpractice.controller;

import com.aweperi.springbootpractice.model.User;
import com.aweperi.springbootpractice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("")
    public List<User> getUsers() {
        return userService.fetchUsers();
    }
    @GetMapping("/confirmed")
    public String message() {
        return "You are now confirmed";
    }
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }
}
