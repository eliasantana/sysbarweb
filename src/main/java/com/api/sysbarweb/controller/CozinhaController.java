package com.api.sysbarweb.controller;
import com.api.sysbarweb.dto.CozinhaDto;
import com.api.sysbarweb.model.Cozinha;
import com.api.sysbarweb.model.Empresa;
import com.api.sysbarweb.model.Funcionario;
import com.api.sysbarweb.services.CozinhaServices;
import com.api.sysbarweb.services.UtilsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cozinha")
public class CozinhaController {
    @Autowired
    CozinhaServices services;
    @Autowired
    UtilsServices utilsServices;

    @GetMapping("/status/{idemplogada}/{idfuncionario}")
    public ResponseEntity<List<CozinhaDto>> statusCozinha(@PathVariable Long idemplogada,
                                                          @PathVariable Long idfuncionario){
       return services.statusCozinha(idemplogada, idfuncionario);

    }

    @PostMapping("/iniciar/{idemplogada}/{idfuncionario}/{idcozinha}")
    public ResponseEntity<CozinhaDto>iniciarPrepato(@PathVariable Long idemplogada,
                                                    @PathVariable Long idfuncionario,
                                                    @PathVariable Long idcozinha){
        Optional<Empresa> empresa = utilsServices.validaEmpresaLogada(idemplogada);
        List<Funcionario> funcionario = utilsServices.validaFuncionario(idemplogada,idfuncionario);
        utilsServices.validaCozinheiro(funcionario.get(0));
        Optional<Cozinha> prato = services.localizaPrato(idcozinha);
        prato.get().setCdFuncionario(idfuncionario);
        prato.get().setNmFuncionario(funcionario.get(0).getNome());
        prato.get().setHoraPrepacacao(LocalDateTime.now());
        prato.get().setStatus("E");
        CozinhaDto cozinhaDto= services.iniciarPreparo(prato);
        return ResponseEntity.ok(cozinhaDto);
    }

    @PostMapping("/liberar/{idemplogada}/{idfuncionario}/{idcozinha}")
    public ResponseEntity<CozinhaDto>liberarPrato(@PathVariable Long idemplogada,
                                                  @PathVariable Long idfuncionario,
                                                  @PathVariable Long idcozinha) {
        List<Funcionario> funcionario = utilsServices.validaFuncionario(idemplogada, idfuncionario);
        Optional<Cozinha> prato = null;
        if (utilsServices.validaCozinheiro(funcionario.get(0))) {
            Optional<Empresa> empresa = utilsServices.validaEmpresaLogada(idemplogada);
            prato = services.localizaPrato(idcozinha);
            LocalDateTime fim = LocalDateTime.now();
            LocalDateTime inicio = prato.get().getHoraPrepacacao();
            prato.get().setTempoPreparacao(services.calculaTempoPreparacao(inicio, fim));
        }

        return services.liberarPrato(prato);
    }

}
