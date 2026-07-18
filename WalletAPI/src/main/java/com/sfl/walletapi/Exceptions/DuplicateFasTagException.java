package com.sfl.walletapi.Exceptions;

public class DuplicateFasTagException extends RuntimeException {
    public DuplicateFasTagException(String message) {
        super(message);
    }
}
