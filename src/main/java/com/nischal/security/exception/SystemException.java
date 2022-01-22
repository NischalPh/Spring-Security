package com.nischal.security.exception;

public class SystemException extends RuntimeException{

    public SystemException(String message) {
        super(message);
    }

    public SystemException() {
        super("System Error!");
    }
}
