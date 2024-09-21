package com.api.sysbarweb.exception;

public class EmpresaException extends RuntimeException {
    public EmpresaException(String message) {
        super(message);
    }

    public EmpresaException(Throwable cause) {
        super(cause);
    }
}
