package com.api.sysbarweb.controller;

import com.api.sysbarweb.dto.ProdutoDto;
import com.api.sysbarweb.dto.ProdutoEstoqueDto;
import com.api.sysbarweb.services.ProdutoServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/produto")
@Tag(name = "Produto", description = "Gerenciamento de Produtos")
public class ProdutoController {

    @Autowired
    ProdutoServices services;
    @Operation(summary = "Adiciona um produto ao Pedido informado",
    responses ={@ApiResponse(responseCode = "200", description = "Sucesso"),
                @ApiResponse(responseCode = "500", description = "Erro interno")})
    @PostMapping
    public ResponseEntity<ProdutoServices>adicionar(@RequestBody ProdutoDto dto, UriComponentsBuilder builder){
        return services.adicionar(dto, builder);
    }
    @Operation(summary = "Lista um produto",
    responses = {@ApiResponse(responseCode = "200", description = "Sucesso"),
                 @ApiResponse(responseCode = "500", description = "Erro interno")})
    @GetMapping("/listar/{idproduto}")
    public ResponseEntity<ProdutoDto>localizar(@PathVariable Long idproduto){
        return services.localizar(idproduto);
    }
    @Operation(summary = "Retorna a lista de todos os produtos ativos no estoque",
            responses = {@ApiResponse(responseCode = "200", description = "Sucesso"),
                         @ApiResponse(responseCode = "500", description = "Erro interno")})
    @GetMapping("/listar")
    public ResponseEntity<List<ProdutoDto>>listarTodos(){
        return services.listarTodos();
    }
    @Operation(summary = "Retorna a lista de todos os produtos da empresa logada",
            responses = {@ApiResponse(responseCode = "200", description = "Sucesso"),
                         @ApiResponse(responseCode = "500", description = "Erro interno")})
    @GetMapping("/listartodos/{cdemplogada}")
    public ResponseEntity<List<ProdutoDto>>listarTodosOsProdutosEmpLogada( @PathVariable Long cdemplogada){
        return services.listarTodosOsProdutosEmpLogada(cdemplogada);
    }
    @Operation(summary = "Exclui Produto",
            responses = {@ApiResponse(responseCode = "200", description = "Sucesso"),
                         @ApiResponse(responseCode = "500", description = "Erro interno")})
    @PostMapping("/excluir/{idproduto}")
    public ResponseEntity<ProdutoDto>excluir(@PathVariable Long idproduto){
        return services.excluir(idproduto);
    }
    @Operation(summary = "Altera um Produto",
            responses = {@ApiResponse(responseCode = "200", description = "Sucesso"),
                         @ApiResponse(responseCode = "500", description = "Erro interno")})
    @PutMapping("/alterar")
    public ResponseEntity<ProdutoDto>altear(@RequestBody ProdutoDto dto){
        return services.alterar(dto);
    }
    @Operation(summary = "Inclui um Produto ao estoque informado",
            responses = {@ApiResponse(responseCode = "200", description = "Sucesso"),
                         @ApiResponse(responseCode = "500", description = "Erro interno")})
    @PostMapping("/adicionaestoque/{idemplogada}")
    public ResponseEntity<ProdutoEstoqueDto>adicionarProdutoEstoque(@PathVariable Long idemplogada,
                                                                    @RequestBody ProdutoEstoqueDto dto,
                                                                    UriComponentsBuilder builder){
        return services.adicionarProdutoEstoque(idemplogada,dto, builder);
    }





}
