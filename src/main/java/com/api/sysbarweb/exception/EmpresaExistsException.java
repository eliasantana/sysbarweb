package com.api.sysbarweb.exception;

public class EmpresaExistsException extends RuntimeException {
    public EmpresaExistsException(String message) {
        super(message);
    }

    public EmpresaExistsException(Throwable cause) {
        super(cause);
    }
}
