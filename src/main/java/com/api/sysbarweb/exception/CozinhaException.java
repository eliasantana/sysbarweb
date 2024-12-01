package com.api.sysbarweb.exception;

public class CargoException extends RuntimeException{

    private CargoException() {
    }

    public CargoException(String message) {
        super(message);
    }

    public CargoException(Throwable cause) {
        super(cause);
    }
}
