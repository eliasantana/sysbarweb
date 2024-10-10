package com.api.sysbarweb.exception;

public class EstoqueException extends RuntimeException{

    private EstoqueException() {
    }

    public EstoqueException(String message) {
        super(message);
    }

    public EstoqueException(Throwable cause) {
        super(cause);
    }
}
