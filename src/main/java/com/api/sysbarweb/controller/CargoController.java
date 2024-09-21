package com.api.sysbarweb.controller;

import com.api.sysbarweb.dto.CargoDto;
import com.api.sysbarweb.services.CargoServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/cargo")
public class CargoController {

    @Autowired
    CargoServices services;

    @GetMapping("/listar")
    public ResponseEntity<List<CargoDto>>listar(){
        return services.listar();
    }

    @PutMapping("/adicionar")
    public ResponseEntity<CargoDto> adicionar(@RequestBody @Valid CargoDto dto, UriComponentsBuilder builder){
        System.out.println(dto);
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
