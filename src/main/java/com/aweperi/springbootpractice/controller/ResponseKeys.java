package com.aweperi.springbootpractice.controller;

public enum ResponseKeys {
    STATUS("status"),
    MESSAGE("message"),
    PAYLOAD("payload"),
    ;

    private final String keys;
    ResponseKeys(String responseKeys) {
        this.keys = responseKeys;
    }

    @Override
    public String toString() {
        return this.keys;
    }
}
