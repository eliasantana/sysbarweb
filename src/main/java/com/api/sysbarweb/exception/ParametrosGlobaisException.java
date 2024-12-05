package com.api.sysbarweb.exception;

public class ParametrosGlobaisException extends RuntimeException{

    private ParametrosGlobaisException() {
    }

    public ParametrosGlobaisException(String message) {
        super(message);
    }

    public ParametrosGlobaisException(Throwable cause) {
        super(cause);
    }
}
