package com.api.sysbarweb.controller;

import com.api.sysbarweb.dto.FuncionarioDto;
import com.api.sysbarweb.dto.LoginDto;
import com.api.sysbarweb.dto.LoginValidate;
import com.api.sysbarweb.services.FuncionarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AutenticacaoController {
    @Autowired
    FuncionarioServices services;
    @PostMapping("/autentica")
    public ResponseEntity<LoginValidate> autentica(@RequestBody LoginDto dto){
        System.out.println("CPF "+dto.login());
        System.out.println("Senha "+dto.senha());

      return services.validaAutenticacao(dto.login(), dto.senha());
    }
}
