package com.SIMHM.exception;

public class StationInventoryException extends RuntimeException {
    public StationInventoryException(String message) {
        super(message);
    }

    public StationInventoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
