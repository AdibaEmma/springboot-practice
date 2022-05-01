package com.aweperi.springbootpractice.model;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;


class UserTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
public void userModelTest() {
        var user = new User("", "", "eabaagah@gmial.com", "");

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertFalse(violations.isEmpty());
    }

}