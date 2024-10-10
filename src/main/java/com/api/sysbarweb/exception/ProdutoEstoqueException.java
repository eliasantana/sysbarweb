package com.api.sysbarweb.exception;

public class ProdutoEstoqueException extends RuntimeException{

    private ProdutoEstoqueException() {
    }

    public ProdutoEstoqueException(String message) {
        super(message);
    }

    public ProdutoEstoqueException(Throwable cause) {
        super(cause);
    }
}
