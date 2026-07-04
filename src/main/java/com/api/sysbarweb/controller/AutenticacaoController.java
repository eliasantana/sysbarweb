package com.api.sysbarweb.controller;

import com.api.sysbarweb.dto.FuncionarioDto;
import com.api.sysbarweb.dto.LoginDto;
import com.api.sysbarweb.dto.LoginValidate;
import com.api.sysbarweb.services.FuncionarioServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Autenticacao", description = "Autenticação de Usuário")
public class AutenticacaoController {
    @Autowired
    FuncionarioServices services;
    @PostMapping("/autentica")
    public ResponseEntity<LoginValidate> autentica(@RequestBody LoginDto dto){

      return services.validaAutenticacao(dto.login(), dto.senha());
    }
}
