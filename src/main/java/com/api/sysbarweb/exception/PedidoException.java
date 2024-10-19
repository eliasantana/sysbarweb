package com.api.sysbarweb.exception;

public class PedidoException extends RuntimeException{

    private PedidoException() {
    }

    public PedidoException(String message) {
        super(message);
    }

    public PedidoException(Throwable cause) {
        super(cause);
    }
}
