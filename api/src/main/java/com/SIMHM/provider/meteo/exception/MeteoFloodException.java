package com.SIMHM.provider.meteo.exception;

public class MeteoFloodException extends RuntimeException {

    public MeteoFloodException(String message) {
        super(message);
    }

    public MeteoFloodException(String message, Throwable cause) {
        super(message, cause);
    }
}
