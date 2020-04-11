package com.iac.webshop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.xml.bind.ValidationException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final HttpServerErrorException handleAllExceptions(Exception ex) {
        return new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, ex.toString());
    }

    @ExceptionHandler(ValidationException.class)
    public final HttpServerErrorException handleValidationException(ValidationException ex) {
        return new HttpServerErrorException(HttpStatus.BAD_REQUEST, ex.toString());
    }

//    @ExceptionHandler(NotFoundException.class)
//    public final HttpServerErrorException handleNotFoundException(NotFoundException ex) {
//        return new HttpServerErrorException(HttpStatus.NOT_FOUND, ex.toString());
//    }

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String handleNotFoundException(NotFoundException ex) {
        return ex.getMessage();
    }
}
