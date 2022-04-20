package com.careerdevs.GoRestSQL.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiErrorHandeling {
    public static ResponseEntity<?> customApiError(String message) {
    }

    public static ResponseEntity<?> customApiError(String message, HttpStatus statusCode) {
    }

    public static ResponseEntity<?> genericApiError(Exception e) {
    }
}
