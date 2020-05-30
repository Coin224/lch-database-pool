package com.lch.dbpool.exception;

public class NoConnExeception extends RuntimeException {

    public NoConnExeception(String message) {
        super(message);
    }
}
