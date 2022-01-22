package com.nischal.security.exception;

public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException() {
        super("Data not found!");
    }
}
