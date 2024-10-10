package com.api.sysbarweb.controller;

import com.api.sysbarweb.dto.MesaDto;
import com.api.sysbarweb.model.Mesa;
import com.api.sysbarweb.services.MesaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/mesa")
public class MesaController {
    @Autowired
    MesaServices services;

    @GetMapping("/listar/{idempresa}")
    public ResponseEntity<List<MesaDto>>listarTodas(@PathVariable Long idempresa){
        return services.listarTodas(idempresa);
    }

    @PostMapping("/adicionar/{idemplogada}/{nrmesa}/{idfuncionario}")
    public ResponseEntity<MesaDto>adicionar(@PathVariable Long idemplogada,
                                            @PathVariable int nrmesa,
                                            @PathVariable Long idfuncionario,
                                            UriComponentsBuilder builder){
        return services.adicionar(idemplogada, nrmesa, idfuncionario, builder);
    }

    @GetMapping("/garcom/{idemplogada}/{idfuncionario}")
    public ResponseEntity<List<MesaDto>>listarMesasPorGarcon(@PathVariable Long idemplogada,
                                                             @PathVariable Long idfuncionario){
        return services.listarMesasGarcom(idemplogada, idfuncionario);
    }

    @PostMapping("/intervalo/{idemplogada}/{nrmesainicial}/{nrmesafinal}/{idfuncionario}")
    public ResponseEntity<List<MesaDto>>intervaloMesa(@PathVariable Long idemplogada,
                                                      @PathVariable Long nrmesainicial,
                                                      @PathVariable Long nrmesafinal,
                                                      @PathVariable Long idfuncionario){

        return services.intervaloMesa(idemplogada, nrmesainicial, nrmesafinal,idfuncionario);
    }
}
