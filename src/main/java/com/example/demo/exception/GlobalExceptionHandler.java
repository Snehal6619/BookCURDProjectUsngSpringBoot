package com.example.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class GlobalExceptionHandler {

	@ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorModel> handle(BookNotFoundException ex) {

        ErrorModel error = new ErrorModel();
        error.setStatusCode(404);
        error.setErrorMassage(ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
