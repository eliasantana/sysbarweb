package com.api.sysbarweb.controller;

import com.api.sysbarweb.dto.ProdutoDto;
import com.api.sysbarweb.dto.ProdutoEstoqueDto;
import com.api.sysbarweb.services.ProdutoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoServices services;

    @PostMapping
    public ResponseEntity<ProdutoServices>adicionar(@RequestBody ProdutoDto dto, UriComponentsBuilder builder){
        return services.adicionar(dto, builder);
    }
    @GetMapping("/listar/{idproduto}")
    public ResponseEntity<ProdutoDto>localizar(@PathVariable Long idproduto){
        return services.localizar(idproduto);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProdutoDto>>listarTodos(){
        return services.listarTodos();
    }
    @PostMapping("/excluir/{idproduto}")
    public ResponseEntity<ProdutoDto>excluir(@PathVariable Long idproduto){
        return services.excluir(idproduto);
    }

    @PutMapping("/alterar")
    public ResponseEntity<ProdutoDto>altear(@RequestBody ProdutoDto dto){
        return services.alterar(dto);
    }

    @PostMapping("/adicionaestoque/{idemplogada}")
    public ResponseEntity<ProdutoEstoqueDto>adicionarProdutoEstoque(@PathVariable Long idemplogada,
                                                                    @RequestBody ProdutoEstoqueDto dto,
                                                                    UriComponentsBuilder builder){
        return services.adicionarProdutoEstoque(idemplogada,dto, builder);
    }





}
