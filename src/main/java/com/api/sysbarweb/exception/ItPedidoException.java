package com.api.sysbarweb.exception;

public class ItPedidoException extends RuntimeException{

    private ItPedidoException() {
    }

    public ItPedidoException(String message) {
        super(message);
    }

    public ItPedidoException(Throwable cause) {
        super(cause);
    }
}
