package com.api.sysbarweb.exception;

public class FuncionarioException extends RuntimeException{

    private FuncionarioException() {
    }

    public FuncionarioException(String message) {
        super(message);
    }

    public FuncionarioException(Throwable cause) {
        super(cause);
    }
}
