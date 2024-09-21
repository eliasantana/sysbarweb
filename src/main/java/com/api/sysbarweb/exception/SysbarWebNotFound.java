package com.api.sysbarweb.exception;

public class SysbarWebNotFound extends  RuntimeException{
    public SysbarWebNotFound(String message) {
        super(message);
    }

    public SysbarWebNotFound(Throwable cause) {
        super(cause);
    }
}
