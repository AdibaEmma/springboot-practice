package com.aweperi.springbootpractice.service;

import com.aweperi.springbootpractice.exceptions.*;
import com.aweperi.springbootpractice.model.ConfirmationToken;
import com.aweperi.springbootpractice.model.User;
import com.aweperi.springbootpractice.repository.UserRepository;
import com.aweperi.springbootpractice.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements UserDetailsService {
    private final BCryptPasswordEncoder bcryptPasswordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final ConfirmationTokenService confirmationTokenService;
    private static final String USER_NOT_FOUND_MSG = "user with email %s not found";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(User user) {
        var userExists = userRepository.findByEmail(user.getEmail()).isPresent();

        if(userExists) throw new UserRegistrationException(new DuplicateEmailException());

        var encodedPassword = bcryptPasswordEncoder.encode(user.getPassword());

        var userRole = userRoleRepository.findRoleByName("USER").orElseThrow(RoleNotFoundException::new);
        user.setPassword(encodedPassword);
        user.addRoleToUser(userRole);

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

    public void enableUser(String email) {
        var user = userRepository.findByEmail(email);
        if(user.isEmpty()) throw new UserAccountException(new UserNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
        var existingUser = user.get();
        existingUser.setEnabled(true);
        userRepository.save(existingUser);
    }

    public List<User> fetchUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
            return userRepository.findById(id).orElseThrow(() -> new UserAccountException(new UserNotFoundException()));
    }
}
