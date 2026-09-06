package com.api.sysbarweb.controller;

import com.api.sysbarweb.dto.MesaDto;
import com.api.sysbarweb.model.Mesa;
import com.api.sysbarweb.services.MesaServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/mesa")
@Tag(name = "Mesa", description = "Controle e Gerenciamento de Mesas")
public class MesaController {
    @Autowired
    MesaServices services;

    @Operation(summary = "Lista todas as mesas de uma empresa selecionada.")
    @GetMapping("/listar/{idempresa}")
    public ResponseEntity<List<MesaDto>>listarTodas(@PathVariable Long idempresa){
        return services.listarTodas(idempresa);
    }
    @Operation(summary = "Adiciona uma mesa para um funcionário na empresa informada.")
    @PostMapping("/adicionar/{idemplogada}/{nrmesa}/{idfuncionario}")
    public ResponseEntity<MesaDto>adicionar(@PathVariable Long idemplogada,
                                            @PathVariable int nrmesa,
                                            @PathVariable Long idfuncionario,
                                            UriComponentsBuilder builder){
        return services.adicionar(idemplogada, nrmesa, idfuncionario, builder);
    }
    @Operation(summary = "Listar as mesas de um garçom na empresa logada.")
    @GetMapping("/garcom/{idemplogada}/{idfuncionario}")
    public ResponseEntity<List<MesaDto>>listarMesasPorGarcon(@PathVariable Long idemplogada,
                                                             @PathVariable Long idfuncionario){
        return services.listarMesasGarcom(idemplogada, idfuncionario);
    }
    @Operation(summary = "Adiciona mesas a partir de um intervalo para a Empresa e Funcionário informados.")
    @PostMapping("/intervalo/{idemplogada}/{nrmesainicial}/{nrmesafinal}/{idfuncionario}")
    public ResponseEntity<List<MesaDto>>intervaloMesa(@PathVariable Long idemplogada,
                                                      @PathVariable Long nrmesainicial,
                                                      @PathVariable Long nrmesafinal,
                                                      @PathVariable Long idfuncionario){

        return services.intervaloMesa(idemplogada, nrmesainicial, nrmesafinal,idfuncionario);
    }
    @Operation(summary = "Altera o funcionário responsável pela mesa")
    @PostMapping("/alterar/{idemplogada}/{idmesa}/{idnovogarcom}")
    public ResponseEntity<MesaDto> alterarAlteraGarcom(@PathVariable Long idemplogada,
                                                       @PathVariable int idmesa,
                                                       @PathVariable Long idnovogarcom){
        return services.alterarAlteraGarcom(idemplogada, idmesa, idnovogarcom);
    }
    @Operation(summary = "Exclui uma mesa a partir do seu código")
    @DeleteMapping("/excluir/{idmesa}")
    public ResponseEntity<MesaDto> excluirMesa(@PathVariable  Long idmesa){
        return services.excluir(idmesa);
    }

}
