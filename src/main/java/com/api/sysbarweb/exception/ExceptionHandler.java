package com.api.sysbarweb.exception;

import com.api.sysbarweb.model.ParametrosGlobais;
import com.api.sysbarweb.model.Produto;
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
    @org.springframework.web.bind.annotation.ExceptionHandler(FuncionarioException.class)
    public ResponseEntity<StandardError>funcionarioExcelption (FuncionarioException ex, HttpServletRequest request){
        StandardError error = new StandardError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "CargoException",
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FOUND).body(error);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(ProdutoException.class)
    public ResponseEntity<StandardError>funcionarioExcelption (ProdutoException ex, HttpServletRequest request){
        StandardError error = new StandardError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "ProdutoException",
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FOUND).body(error);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MesaException.class)
    public ResponseEntity<StandardError>mesaException (MesaException ex, HttpServletRequest request){
        StandardError error = new StandardError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "MesaException",
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FOUND).body(error);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(EstoqueException.class)
    public ResponseEntity<StandardError>funcionarioExcelption (EstoqueException ex, HttpServletRequest request){
        StandardError error = new StandardError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "CargoException",
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FOUND).body(error);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(ProdutoEstoqueException.class)
    public ResponseEntity<StandardError>funcionarioExcelption (ProdutoEstoqueException ex, HttpServletRequest request){
        StandardError error = new StandardError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "CargoException",
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FOUND).body(error);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(PedidoException.class)
    public ResponseEntity<StandardError>funcionarioExcelption (PedidoException ex, HttpServletRequest request){
        StandardError error = new StandardError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "CargoException",
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FOUND).body(error);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(ItPedidoException.class)
    public ResponseEntity<StandardError>funcionarioExcelption (ItPedidoException ex, HttpServletRequest request){
        StandardError error = new StandardError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "PedidoException",
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FOUND).body(error);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(ParametrosGlobaisException.class)
    public ResponseEntity<StandardError>funcionarioExcelption (ParametrosGlobaisException ex, HttpServletRequest request){
        StandardError error = new StandardError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "ParametrosGlobaisException",
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FOUND).body(error);
    }
}
