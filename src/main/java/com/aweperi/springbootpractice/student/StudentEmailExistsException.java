package com.aweperi.springbootpractice.student;

public class StudentEmailExistsException extends Exception{
    public StudentEmailExistsException() {
        super("email already exists!");
    }

    public StudentEmailExistsException(String message) {
        super(message);
    }

}
