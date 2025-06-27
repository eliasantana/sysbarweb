package com.api.sysbarweb.controller;
import com.api.sysbarweb.dto.CozinhaDto;
import com.api.sysbarweb.exception.CozinhaException;
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

    @GetMapping("/status/{idemplogada}/{nrmesa}")
    public ResponseEntity<List<CozinhaDto>> statusCozinha(@PathVariable Long idemplogada,
                                                          @PathVariable Long nrmesa){
       return services.statusCozinha(idemplogada, nrmesa);

    }

    @PostMapping("/iniciar/{idemplogada}/{idfuncionario}/{idcozinha}")
    public ResponseEntity<CozinhaDto>iniciarPrepato(@PathVariable Long idemplogada,
                                                    @PathVariable Long idfuncionario,
                                                    @PathVariable Long idcozinha,
                                                    @RequestParam (name = "obs", required = false) String obs){
        Optional<Empresa> empresa = utilsServices.validaEmpresaLogada(idemplogada);
        List<Funcionario> funcionario = utilsServices.validaFuncionario(idemplogada,idfuncionario);
        utilsServices.validaCozinheiro(funcionario.get(0));
        Optional<Cozinha> prato = services.localizaPrato(idcozinha);
        if (prato.get().getHoraPrepacacao()!=null){
            throw  new CozinhaException("Prato já iniciado!");
        }
        prato.get().setCdFuncionario(idfuncionario);
        prato.get().setNmFuncionario(funcionario.get(0).getNome());
        prato.get().setHoraPrepacacao(LocalDateTime.now());
        prato.get().setStatus("E"); //E - Em Preparação
        prato.get().setObservacao(obs);
        CozinhaDto cozinhaDto=services.iniciarPreparo(prato);
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
            Optional<Cozinha> p =utilsServices.validaprato(prato);
            LocalDateTime fim = LocalDateTime.now();
            LocalDateTime inicio = prato.get().getHoraPrepacacao();
            prato.get().setTempoPreparacao(services.calculaTempoPreparacao(inicio, fim));
            prato.get().setStatus("L"); //P - PENDENTE E - EM PREPARAÇÃO L - LIBERADO
        }

        return services.liberarPrato(prato);
    }
    @PostMapping("/remover/{idemplogada}/{idfuncionario}/{idcozinha}")
    public ResponseEntity<CozinhaDto>removerPrato(@PathVariable Long idemplogada,
                                                  @PathVariable Long idfuncionario,
                                                  @PathVariable Long idcozinha){
        return services.removerPrato(idemplogada, idfuncionario, idcozinha);
    }

}
