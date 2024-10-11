package com.api.sysbarweb.controller;

import com.api.sysbarweb.dto.EstoqueDto;
import com.api.sysbarweb.dto.ProdutoDto;
import com.api.sysbarweb.dto.ProdutoEstoqueDto;
import com.api.sysbarweb.model.Estoque;
import com.api.sysbarweb.model.ProdutoEstoque;
import com.api.sysbarweb.services.EstoqueServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
    @Autowired
    EstoqueServices services;
    @GetMapping("/listar/{idemplogada}")
    public ResponseEntity<List<EstoqueDto>>listar(@PathVariable Long idemplogada){
        return  services.listar(idemplogada);
    }
    @PostMapping("/adicionar/{idemplogada}")
    public ResponseEntity<EstoqueDto>adicionar(@RequestBody EstoqueDto dto,
                                               @PathVariable Long idemplogada,
                                               UriComponentsBuilder builder){
        return services.adicionar(dto,idemplogada,builder);
    }

    @PutMapping("/alterar")
    public ResponseEntity<EstoqueDto>alterar(@RequestBody EstoqueDto dto){
        return services.alterar(dto);
    }

    @GetMapping("/produtoestoque/{idestoque}")
    public ResponseEntity<List<ProdutoEstoqueDto>>idestoque(@PathVariable Long idestoque){
        return services.listarProdutoEstoque(idestoque);
    }
}
