package com.api.sysbarweb.controller;

import com.api.sysbarweb.projections.TxEstatisticaProjection;
import com.api.sysbarweb.services.EstatisticaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    @Autowired
    EstatisticaServices services;

    @GetMapping("/ocupacao/{cdempresa}")
    public ResponseEntity<TxEstatisticaProjection> getStatisticaOcupacao(@PathVariable Long cdempresa){
        return services.getStatistica(cdempresa);
    }

}
