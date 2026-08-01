package com.SIMHM.provider.ana.exception;

public class AnaAuthException extends RuntimeException {
    public AnaAuthException(String message) {
        super(message);
    }

    public AnaAuthException(String message, Throwable cause) {
        super(message, cause);
    }
}
