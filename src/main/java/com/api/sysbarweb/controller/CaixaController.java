package com.api.sysbarweb.controller;

import com.api.sysbarweb.dto.CaixaDto;
import com.api.sysbarweb.services.CaixaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/caixa")
public class CaixaController {
    @Autowired
    CaixaServices services;

    @PostMapping("/abrir/{idemplogada}/{idfuncionario}")
    public ResponseEntity<CaixaDto>abrirCaixa(@PathVariable Long idemplogada,
                                              @PathVariable Long idfuncionario,
                                              @RequestParam(name = "pw") String password,
                                              @RequestParam(name = "saldo") BigDecimal saldoinicial){

        return services.abrirCaixa(idemplogada, idfuncionario,password,saldoinicial);
    }

    @PostMapping("/fechar/{idemplogada}/{idfuncionario}")
    public ResponseEntity<CaixaDto>fecharCaixa(
            @PathVariable Long idemplogada,
            @PathVariable Long idfuncionario,
            @RequestParam(name = "pw") String password
    ){
        return services.fecharCaixa(idemplogada, idfuncionario, password);
    }
}
