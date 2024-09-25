package com.api.sysbarweb.exception;

public class ProdutoException extends RuntimeException{

    private ProdutoException() {
    }

    public ProdutoException(String message) {
        super(message);
    }

    public ProdutoException(Throwable cause) {
        super(cause);
    }
}
