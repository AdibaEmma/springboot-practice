package com.aweperi.springbootpractice.service;

import com.aweperi.springbootpractice.exceptions.DuplicateEmailException;
import com.aweperi.springbootpractice.exceptions.UserRegistrationException;
import com.aweperi.springbootpractice.model.User;
import com.aweperi.springbootpractice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private static final String USER_NOT_FOUND_MSG = "user with email %s not found";

    private final UserRepository userRepository;
    private BCryptPasswordEncoder bcryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(User user) {
        var userExists = userRepository.findByEmail(user.getEmail()).isPresent();

        if(userExists) throw new UserRegistrationException(new DuplicateEmailException());

        var encodedPassword = bcryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

//        Todo: send confirmation token
        return "saved";
    }
}
