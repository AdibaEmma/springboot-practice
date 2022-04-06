package com.aweperi.springbootpractice.service;

import com.aweperi.springbootpractice.exceptions.InvalidTokenException;
import com.aweperi.springbootpractice.exceptions.UserAccountException;
import com.aweperi.springbootpractice.model.ConfirmationToken;
import com.aweperi.springbootpractice.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public void setConfirmedAt(String token) {
        var confirmationToken = confirmationTokenRepository.findByToken(token);

        if(confirmationToken.isEmpty()) throw new UserAccountException(new InvalidTokenException());
        var existingToken = confirmationToken.get();
        existingToken.setConfirmedAt(LocalDateTime.now());
        confirmationTokenRepository.save(existingToken);
    }
}
