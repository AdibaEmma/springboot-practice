package com.aweperi.springbootpractice.service;

import java.util.function.Predicate;

public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
//        Todo: implement regex
        return true;
    }
}
