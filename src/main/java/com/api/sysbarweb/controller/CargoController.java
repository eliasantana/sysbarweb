package com.api.sysbarweb.controller;

import com.api.sysbarweb.dto.CargoDto;
import com.api.sysbarweb.services.CargoServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/cargo")
@Tag(name = "Cargo", description = "Controle e Gerenciamento de Cargos")
public class CargoController {

    @Autowired
    CargoServices services;

    @GetMapping("/listar")
    public ResponseEntity<List<CargoDto>>listar(){
        return services.listar();
    }

    @PutMapping("/adicionar")
    public ResponseEntity<CargoDto> adicionar(@RequestBody @Valid CargoDto dto, UriComponentsBuilder builder){

        return services.adicionar(dto,builder);
    }

    @PostMapping("/alterar")
    public ResponseEntity<CargoDto>alterar(@RequestBody @Valid CargoDto dto, UriComponentsBuilder builder){
        return services.alterar(dto);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<CargoDto>excluir(@PathVariable Long id){
        return services.excluir(id);
    }

}
