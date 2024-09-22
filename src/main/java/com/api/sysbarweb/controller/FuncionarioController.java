package com.api.sysbarweb.controller;

import com.api.sysbarweb.dto.FuncionarioDto;
import com.api.sysbarweb.model.Funcionario;
import com.api.sysbarweb.services.FuncionarioServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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

    @PostMapping("/transferir/{idemplogada}/{idfuncionario}/{idempdestino}")
    public ResponseEntity<Funcionario>transferir(@PathVariable Long idemplogada,
                                                 @PathVariable Long idfuncionario,
                                                 @PathVariable Long idempdestino
                                                 ){
        return services.transferir(idemplogada,idfuncionario,idempdestino);
    }

    @PostMapping("/adicionar/{idemplogada}/{idcargo}")
    public ResponseEntity<FuncionarioDto>adicionar(@PathVariable Long idemplogada,
                                                   @PathVariable Long idcargo,
                                                   @RequestBody FuncionarioDto dto, UriComponentsBuilder builder){
       return services.adicionarFuncionario(dto, idemplogada, idcargo,builder);
    }
    @PostMapping("/excluir/{idemplogada}/{idfuncionario}")
    public ResponseEntity<FuncionarioDto>excluir(@PathVariable Long idemplogada,
                                                 @PathVariable Long idfuncionario){
        return services.excluirFuncinario(idemplogada,idfuncionario);
    }

    @PostMapping("/alterar/{idemplocada}")
    public ResponseEntity<FuncionarioDto>alterar(@RequestBody FuncionarioDto dto,
                                                 @PathVariable Long idemplocada, UriComponentsBuilder builder){
        return services.alterar(dto, idemplocada, builder);
    }

    @PostMapping("/promover/{idemplogada}/{idfuncionario}/{idnovocargo}")
    public ResponseEntity<FuncionarioDto>promoverFuncionario(@PathVariable Long idemplogada,
                                                          @PathVariable Long idfuncionario,
                                                          @PathVariable Long idnovocargo){
        return  services.promoverFuncionario(idemplogada, idfuncionario, idnovocargo);
    }

}
