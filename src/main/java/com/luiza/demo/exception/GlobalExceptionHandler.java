package com.luiza.demo.exception;

import com.luiza.demo.exception.customexception.DomainBusinessException;
import com.luiza.demo.exception.customexception.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundExceptions(Exception exception, WebRequest webRequest) {
        HttpStatus errorCode = HttpStatus.NOT_FOUND;
        HttpErroInfo build = getHttpErroInfo(exception, errorCode);
        return this.handleExceptionInternal(exception, build, new HttpHeaders(), errorCode, webRequest);
    }

    @ExceptionHandler(value = {DomainBusinessException.class})
    public ResponseEntity<Object> handleDomainBusinessExceptions(Exception exception, WebRequest webRequest) {
        HttpStatus errorCode = HttpStatus.UNPROCESSABLE_ENTITY;
        HttpErroInfo build = getHttpErroInfo(exception, errorCode);
        return this.handleExceptionInternal(exception, build, new HttpHeaders(), errorCode, webRequest);
    }

    private static HttpErroInfo getHttpErroInfo(Exception exception, HttpStatus errorCode) {
        return HttpErroInfo.builder().code(errorCode.value()).description(exception.getMessage()).build();
    }
}
