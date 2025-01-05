package com.api.sysbarweb.exception;

public class CaixaException extends RuntimeException{

    private CaixaException() {
    }

    public CaixaException(String message) {
        super(message);
    }

    public CaixaException(Throwable cause) {
        super(cause);
    }
}
