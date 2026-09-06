package com.api.sysbarweb.controller;

import com.api.sysbarweb.dto.FuncionarioDto;
import com.api.sysbarweb.model.Funcionario;
import com.api.sysbarweb.services.FuncionarioServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/funcionario")
@Tag(name = "Funcionário", description = "Gerenciamento e Controle dos Funcionários")
public class FuncionarioController {

    @Autowired
    FuncionarioServices services;
    @Operation(summary = "Lista os funcionários da empresa informada")
    @GetMapping("/listar/{idempresa}")
    public ResponseEntity<List<FuncionarioDto>> listar(@PathVariable Long idempresa){
        return services.listar(idempresa);
    }
    @Operation(summary = "Teste de paginação")
    @GetMapping("/teste/{idempresa}")
    public ResponseEntity<List<FuncionarioDto>> listarTeste(@PathVariable Long idempresa,
                                                            @RequestParam(name = "pagina", defaultValue = "0") Long pagina,
                                                            @RequestParam(name="item", defaultValue = "2") Long item,
                                                            @RequestParam(name="direction",defaultValue = "asc") String direction){
        return services.listarTeste(idempresa, pagina, item,direction);
    }
    @Operation(summary = "Transferir um funcioário")
    @PostMapping("/transferir/{idemplogada}/{idfuncionario}/{idempdestino}")
    public ResponseEntity<Funcionario>transferir(@PathVariable Long idemplogada,
                                                 @PathVariable Long idfuncionario,
                                                 @PathVariable Long idempdestino
                                                 ){
        return services.transferir(idemplogada,idfuncionario,idempdestino);
    }
    @Operation(summary = "Adiciona um funcionário")
    @PostMapping("/adicionar/{idemplogada}/{idcargo}")
    public ResponseEntity<FuncionarioDto>adicionar(@PathVariable Long idemplogada,
                                                   @PathVariable Long idcargo,
                                                   @RequestBody FuncionarioDto dto, UriComponentsBuilder builder){
       return services.adicionarFuncionario(dto, idemplogada, idcargo,builder);
    }
    @Operation(summary = "Realiza a exclusão de um funcionário ")
    @DeleteMapping("/excluir/{idemplogada}/{idfuncionario}")
    public ResponseEntity<FuncionarioDto>excluir(@PathVariable Long idemplogada,
                                                 @PathVariable Long idfuncionario){
        return services.excluirFuncinario(idemplogada,idfuncionario);
    }
    @Operation(summary = "Altera o funcionário informado")
    @PostMapping("/alterar/{idemplocada}")
    public ResponseEntity<FuncionarioDto>alterar(@RequestBody FuncionarioDto dto,
                                                 @PathVariable Long idemplocada, UriComponentsBuilder builder){
        return services.alterar(dto, idemplocada, builder);
    }
    @Operation(summary = "Promove um funcionário para o cargo informado")
    @PostMapping("/promover/{idemplogada}/{idfuncionario}/{idnovocargo}")
    public ResponseEntity<FuncionarioDto>promoverFuncionario(@PathVariable Long idemplogada,
                                                          @PathVariable Long idfuncionario,
                                                          @PathVariable Long idnovocargo){
        return  services.promoverFuncionario(idemplogada, idfuncionario, idnovocargo);
    }
    @Operation(summary = "Lista o funcionário pelo código informado")
    @GetMapping("/get/{idfuncionario}")
    public ResponseEntity<FuncionarioDto>getFuncionario(@PathVariable Long idfuncionario) {
        return  services.getFuncioanrio(idfuncionario);
    }
    @Operation(summary = "Lista os funcionário pelo código do cargo")
    @GetMapping("/getcargo/{idCargo}")
    public ResponseEntity<List<Funcionario>>getFuncionarioCargo(@PathVariable Long idCargo) {
        return  services.getFuncioanarioCargo(idCargo);
    }

}
