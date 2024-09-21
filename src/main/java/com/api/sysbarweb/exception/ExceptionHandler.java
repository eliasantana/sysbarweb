package com.api.sysbarweb.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(SysbarWebNotFound.class)
    public ResponseEntity<StandardError>sysbarWebNotFound (SysbarWebNotFound ex, HttpServletRequest request){
       StandardError error = new StandardError(System.currentTimeMillis(),
                                               HttpStatus.NOT_FOUND.value(),
                                          "ObjectNotFound",
                                                ex.getMessage(),
                                                request.getRequestURI());
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(EmpresaExistsException.class)
    public ResponseEntity<StandardError>empresaExistsException (EmpresaExistsException ex, HttpServletRequest request){
        StandardError error = new StandardError(System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(),
                "EmpresaExistsException",
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FOUND).body(error);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(EmpresaException.class)
    public ResponseEntity<StandardError>empresaException (EmpresaException ex, HttpServletRequest request){
        StandardError error = new StandardError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "EmpresaException",
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FOUND).body(error);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CargoException.class)
    public ResponseEntity<StandardError>empresaException (CargoException ex, HttpServletRequest request){
        StandardError error = new StandardError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "CargoException",
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FOUND).body(error);
    }

}
