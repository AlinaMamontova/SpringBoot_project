package com.example.springboot_project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandling {
    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(NoSuchElementException exception) {
        IncorrectData incorrectData = new IncorrectData();
        incorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(ElementExists elementExists) {
        IncorrectData incorrectData = new IncorrectData();
        incorrectData.setInfo(elementExists.getMessage());
        return new ResponseEntity<>(incorrectData, HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(Exception exception) {
        IncorrectData incorrectData = new IncorrectData();
        incorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
    }
}
