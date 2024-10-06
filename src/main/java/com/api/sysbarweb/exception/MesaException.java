package com.api.sysbarweb.exception;

public class MesaException extends RuntimeException{

    private MesaException() {
    }

    public MesaException(String message) {
        super(message);
    }

    public MesaException(Throwable cause) {
        super(cause);
    }
}
