package com.example.springboot_project.exception;

public class ElementExists extends RuntimeException {

    public ElementExists(String message) {
        super(message);
    }
}
