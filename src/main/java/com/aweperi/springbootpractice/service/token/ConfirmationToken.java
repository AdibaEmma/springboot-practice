package com.aweperi.springbootpractice.service.token;

import java.time.LocalDateTime;

public class ConfirmationToken {
    private Long id;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private LocalDateTime confirmedAt;
}
