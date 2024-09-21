package com.api.sysbarweb.controller;

import com.api.sysbarweb.dto.EmpresaDto;
import com.api.sysbarweb.services.EmpresaServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    EmpresaServices services;

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDto> getEmpresa(@PathVariable Long id){
        return services.getEmpresa(id);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<EmpresaDto> adicionar(@RequestBody  @Valid EmpresaDto dto, UriComponentsBuilder builder){
        return services.adicionar(dto, builder);
    }

    @PutMapping("/alterar")
    public ResponseEntity<EmpresaDto>alterar(@RequestBody @Valid EmpresaDto dto, UriComponentsBuilder builder){
        return services.alterar(dto,builder);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EmpresaDto> excluir(@PathVariable Long id){
         return  services.deletaEmpresa(id);
    }

    @PutMapping("/ativaempresa/{id}")
    public ResponseEntity<EmpresaDto> ativaempresa(@PathVariable Long id){
        return services.ativaEmpresa( id);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<EmpresaDto>>listarTudo(){
        return services.listarTodas();
    }
}
