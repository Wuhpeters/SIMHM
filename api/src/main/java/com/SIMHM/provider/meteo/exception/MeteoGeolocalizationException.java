package com.SIMHM.provider.meteo.exception;

public class MeteoGeolocalizationException extends RuntimeException {

    public MeteoGeolocalizationException(String message) {
        super(message);
    }

    public MeteoGeolocalizationException(String message, Throwable cause) {
        super(message, cause);
    }
}