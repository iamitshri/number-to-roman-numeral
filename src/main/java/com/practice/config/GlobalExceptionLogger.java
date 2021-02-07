package com.practice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionLogger {

    Logger log = LoggerFactory.getLogger(this.getClass()
                                             .getName());

    /**
     *  <h1>Handler used to log exceptions, so it is available for troubleshooting.</h1>
     * @param ex Exception to be logged
     * @throws Exception rethrow the exception so framework can handle it
     */
    @ExceptionHandler(value = {Exception.class})
    protected void handleException(Exception ex) throws Exception {
        log.error("Exception in conversion:", ex);
        throw ex; // log the exception and let framework handle it
    }
}
