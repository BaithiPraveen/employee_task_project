package com.employee.employee.exceptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();

        Map<String, String> errorMessages = new HashMap<>();

        for (ConstraintViolation<?> violation : violations) {

            // Extract the property path and error message
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();

            // Add the error message to the map
            errorMessages.put(propertyPath, message);
        }

        return ResponseEntity.badRequest().body(errorMessages);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleDuplicateKeyException(DataIntegrityViolationException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Duplicate entry error: " + e.getMessage());
    }
}
