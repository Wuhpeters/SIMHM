package com.SIMHM.provider.meteo.exception;

public class MeteoWeatherException extends RuntimeException {

    public MeteoWeatherException(String message) {
        super(message);
    }

    public MeteoWeatherException(String message, Throwable cause) {
        super(message, cause);
    }
}
