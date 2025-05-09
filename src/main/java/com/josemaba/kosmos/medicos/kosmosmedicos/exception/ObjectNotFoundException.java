package com.josemaba.kosmos.medicos.kosmosmedicos.exception;

public class ObjectNotFoundException extends RuntimeException{
    
    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectNotFoundException() {

    }
}
