package com.aweperi.springbootpractice.student;

public class InvalidIDException extends Exception{
    public InvalidIDException() {
        super("Id does not exist");
    }

    public InvalidIDException(String message) {
        super(message);
    }
}
