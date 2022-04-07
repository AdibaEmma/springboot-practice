package com.aweperi.springbootpractice.email;

public interface EmailSender {
    void send(String to, String email);
}
