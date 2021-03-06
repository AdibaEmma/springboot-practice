package com.aweperi.springbootpractice.service;

import com.aweperi.springbootpractice.email.EmailSender;
import com.aweperi.springbootpractice.exceptions.EmailServiceException;
import com.aweperi.springbootpractice.exceptions.UserAccountException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@AllArgsConstructor
@Slf4j
public class EmailService implements EmailSender {

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String email) {
        try {
            var mimeMessage = mailSender.createMimeMessage();
            var helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom("controllutasmone@gmail.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException ex) {
            log.error("failed to send email", ex);
            throw new UserAccountException(new EmailServiceException());
        }
    }
}
