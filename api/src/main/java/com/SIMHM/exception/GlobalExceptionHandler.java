package com.SIMHM.exception;

import com.SIMHM.provider.ana.exception.AnaAuthException;
import com.SIMHM.provider.ana.exception.AnaHydrologyStationsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AnaAuthException.class)
    public ResponseEntity<ErrorResponse> handleAnaAuthException(
            AnaAuthException ex,
            HttpServletRequest request) {

        return buildErrorResponse(
                HttpStatus.BAD_GATEWAY,
                ex.getMessage(),
                request.getRequestURI());
    }

    @ExceptionHandler(AnaHydrologyStationsException.class)
    public ResponseEntity<ErrorResponse> handleAnaHydrologyStationsException(
            AnaHydrologyStationsException ex,
            HttpServletRequest request) {

        return buildErrorResponse(
                HttpStatus.BAD_GATEWAY,
                ex.getMessage(),
                request.getRequestURI());
    }

    @ExceptionHandler(StationInventoryException.class)
    public ResponseEntity<ErrorResponse> handleStationInventoryException(
            StationInventoryException ex,
            HttpServletRequest request) {

        return buildErrorResponse(
                HttpStatus.BAD_GATEWAY,
                ex.getMessage(),
                request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
            Exception ex,
            HttpServletRequest request) {

        return buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Ocorreu um erro interno inesperado.",
                request.getRequestURI());
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(
            HttpStatus status,
            String message,
            String path) {

        ErrorResponse response = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(path)
                .build();

        return ResponseEntity
                .status(status)
                .body(response);
    }
}