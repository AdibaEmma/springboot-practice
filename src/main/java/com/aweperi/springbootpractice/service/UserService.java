package com.aweperi.springbootpractice.service;

import com.aweperi.springbootpractice.exceptions.DuplicateEmailException;
import com.aweperi.springbootpractice.exceptions.UserRegistrationException;
import com.aweperi.springbootpractice.model.ConfirmationToken;
import com.aweperi.springbootpractice.model.User;
import com.aweperi.springbootpractice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private static final String USER_NOT_FOUND_MSG = "user with email %s not found";
    private BCryptPasswordEncoder bcryptPasswordEncoder;

    private final UserRepository userRepository;
    private final ConfirmationTokenService confirmationTokenService;



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
        String token = UUID.randomUUID().toString();
        var confirmationToken = new ConfirmationToken(
            token,
            LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(15),
            user
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        // TODO: Send Email

        return token;
    }
}
