package com.bestaone.springboot.oauth2.resource.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvisor {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus
    public ViewData exceptionHandler(Exception e) {
        return ViewData.error(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ViewData formatCheckExceptionHandler(RuntimeException e) {
        return ViewData.error(e.getMessage());
    }

}