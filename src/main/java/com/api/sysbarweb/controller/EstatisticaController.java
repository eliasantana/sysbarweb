package com.api.sysbarweb.controller;

import com.api.sysbarweb.projections.TxEstatisticaProjection;
import com.api.sysbarweb.services.EstatisticaServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
