package com.iac.webshop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.xml.bind.ValidationException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final HttpServerErrorException handleAllExceptions(Exception ex) {
        // List<String> details = new ArrayList<>();
        // details.add(ex.toString());
        return new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, ex.toString());
    }

    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public final HttpServerErrorException handleValidationException(ValidationException ex) {
        // List<String> details = new ArrayList<>();
        // details.add(ex.getLocalizedMessage());
        return new HttpServerErrorException(HttpStatus.BAD_REQUEST, ex.toString());
    }
}
