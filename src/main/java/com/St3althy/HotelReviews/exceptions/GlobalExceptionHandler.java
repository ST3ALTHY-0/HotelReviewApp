package com.St3althy.HotelReviews.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Centralized exception handling
 * Huzza no more crazy try catch blocks and tossing around errors like its hot potato
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
        log.warn("Entity not found: {}", ex.getMessage());
        ErrorDetails details = new ErrorDetails(HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(details);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDetails> handleBadRequest(IllegalArgumentException ex, WebRequest request) {
        log.warn("Bad request: {}", ex.getMessage());
        ErrorDetails details = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(details);
    }

    // Fallback for any other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
        log.error("Unhandled exception caught", ex);
        ErrorDetails details = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error", request.getDescription(false));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(details);
    }

}
