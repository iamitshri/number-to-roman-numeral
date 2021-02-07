package com.practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "A valid integer input in the range [1-3999] inclusive is required")
public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String s) {
        super(s);
    }
}
