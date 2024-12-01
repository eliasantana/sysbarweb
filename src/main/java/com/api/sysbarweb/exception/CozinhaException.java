package com.api.sysbarweb.exception;

public class CozinhaException extends RuntimeException{

    private CozinhaException() {
    }

    public CozinhaException(String message) {
        super(message);
    }

    public CozinhaException(Throwable cause) {
        super(cause);
    }
}
