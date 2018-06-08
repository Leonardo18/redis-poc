package com.poc.redis.games.config;

import org.apache.camel.processor.validation.PredicateValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.management.openmbean.KeyAlreadyExistsException;

@ControllerAdvice
public class GlobalExceptionHandler{

    //TODO: refatorar para usar aqui nas exceptions

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new Error(e.getMessage());
    }

    @ExceptionHandler(value = KeyAlreadyExistsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleKeyAlreadyExistsException(KeyAlreadyExistsException e) {
        return new Error(e.getMessage());
    }

    @ExceptionHandler(value = PredicateValidationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handlePredicateValidationException(PredicateValidationException e) {
        return new Error(e.getMessage());
    }
}
