package com.api.sysbarweb.controller;

import com.api.sysbarweb.projections.TxEstatisticaProjection;
import com.api.sysbarweb.services.EstatisticaServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/estatistica")
@Tag(name = "Estatistica", description = "Estatísticas da Empresa Logada")
public class EstatisticaController {

    @Autowired
    EstatisticaServices services;

    @GetMapping("/ocupacao/{cdempresa}")
    public ResponseEntity<TxEstatisticaProjection> getStatisticaOcupacao(@PathVariable Long cdempresa){
        return services.getStatistica(cdempresa);
    }

}
