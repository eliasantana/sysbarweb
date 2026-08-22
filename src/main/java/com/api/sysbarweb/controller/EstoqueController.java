package com.api.sysbarweb.controller;

import com.api.sysbarweb.dto.EstoqueDto;
import com.api.sysbarweb.dto.ProdutoDto;
import com.api.sysbarweb.dto.ProdutoEstoqueDto;
import com.api.sysbarweb.model.Estoque;
import com.api.sysbarweb.model.ProdutoEstoque;
import com.api.sysbarweb.services.EstoqueServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/estoque")
@Tag(name = "Estoque", description = "Gerenciamento do Estoque")
public class EstoqueController {
    @Autowired
    EstoqueServices services;
    @Operation(summary = "Lista os estoques cadastrados",responses = {@ApiResponse(responseCode = "200", description = "Sucesso"),
                                                            @ApiResponse(responseCode = "500", description = "Erro interno")})
    @GetMapping("/listar/{idemplogada}")
    public ResponseEntity<List<EstoqueDto>>listar(@PathVariable Long idemplogada){
        return  services.listar(idemplogada);
    }
    @Operation(summary = "Adiciona um estoque",responses = {@ApiResponse(responseCode = "200", description = "Sucesso"),
                                                            @ApiResponse(responseCode = "500", description = "Erro interno")})
    @PostMapping("/adicionar/{idemplogada}")
    public ResponseEntity<EstoqueDto>adicionar(@RequestBody EstoqueDto dto,
                                               @PathVariable Long idemplogada,
                                               UriComponentsBuilder builder){
        return services.adicionar(dto,idemplogada,builder);
    }
    @Operation(summary = "Altera um estoque",responses = {@ApiResponse(responseCode = "200", description = "Sucesso"),
                                                          @ApiResponse(responseCode = "500", description = "Erro interno")})
    @PutMapping("/alterar")
    public ResponseEntity<EstoqueDto>alterar(@RequestBody EstoqueDto dto){
        return services.alterar(dto);
    }
    @Operation(summary = "Lista os produtos do estoque informado",responses = {@ApiResponse(responseCode = "200", description = "Sucesso"),
                                                            @ApiResponse(responseCode = "500", description = "Erro interno")})
    @GetMapping("/produtoestoque/{idestoque}")
    public ResponseEntity<List<ProdutoEstoqueDto>>idestoque(@PathVariable Long idestoque){
        return services.listarProdutoEstoque(idestoque);
    }
    @Operation(summary = "Exclui o estoque informado",responses = {@ApiResponse(responseCode = "200", description = "Sucesso"),
                                                            @ApiResponse(responseCode = "500", description = "Erro interno")})
    @DeleteMapping("/excluir/{cdestoque}/{idemplocada}")
    public ResponseEntity<EstoqueDto>excluir(@PathVariable Long cdestoque,@PathVariable Long idemplocada){
       return services.excluir(cdestoque,idemplocada);
    }
}
