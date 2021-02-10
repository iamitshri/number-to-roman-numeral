package com.practice.config;

import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    Logger log = LoggerFactory.getLogger(this.getClass()
                                             .getName());

    /**
     * <h1>Handler used to log exceptions, so it is available for troubleshooting.</h1>
     *
     * @param ex Exception to be logged
     * @return Error message in plain text format
     */
    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<String> handleException(Exception ex) {
        log.error("Exception :", ex);
        var httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("text", "plain", StandardCharsets.UTF_8));
        return new ResponseEntity<>(ex.getMessage(), httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
