package com.api.sysbarweb.controller;

import com.api.sysbarweb.dto.FuncionarioDto;
import com.api.sysbarweb.services.FuncionarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    FuncionarioServices services;
    @GetMapping("/listar/{idempresa}")
    public ResponseEntity<List<FuncionarioDto>> listar(@PathVariable Long idempresa){
        return services.listar(idempresa);
    }

}
