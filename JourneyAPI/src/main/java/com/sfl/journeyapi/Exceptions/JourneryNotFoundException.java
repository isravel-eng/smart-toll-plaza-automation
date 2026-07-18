package com.sfl.journeyapi.Exceptions;

public class JourneryNotFoundException extends RuntimeException {
    public JourneryNotFoundException(String message) {
        super(message);
    }
}
