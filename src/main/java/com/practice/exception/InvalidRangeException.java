package com.practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Min must be less than max. Both must be in the supported range[1-3999]")
public class InvalidRangeException extends RuntimeException {

    public InvalidRangeException(String s) {
        super(s);
    }

    public InvalidRangeException() {
    }
}
