package com.api.sysbarweb.services;

import com.api.sysbarweb.dto.EmpresaDto;
import com.api.sysbarweb.dto.EstoqueDto;
import com.api.sysbarweb.dto.ProdutoDto;
import com.api.sysbarweb.dto.ProdutoEstoqueDto;
import com.api.sysbarweb.exception.EmpresaException;
import com.api.sysbarweb.exception.EstoqueException;
import com.api.sysbarweb.model.Empresa;
import com.api.sysbarweb.model.Estoque;
import com.api.sysbarweb.model.Produto;
import com.api.sysbarweb.model.ProdutoEstoque;
import com.api.sysbarweb.repository.EmpresaRepository;
import com.api.sysbarweb.repository.EstoqueRepository;
import com.api.sysbarweb.repository.ProdutoEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstoqueServices {
    @Autowired
    EstoqueRepository repository;
    @Autowired
    ProdutoEstoqueRepository produtoEstoqueRepository;

    @Autowired
    UtilsServices utilsServices;
    public ResponseEntity<List<EstoqueDto>> listar(Long idemplogada) {
        utilsServices.validaEmpresaLogada(idemplogada);
        List<Estoque> estoques = repository.listar(idemplogada);
        if(estoques.isEmpty()){ throw new EstoqueException("Nenhum estoque foi localizado!");}
        List<EstoqueDto> estoqueDtos=estoques.stream()
                .map(e -> new EstoqueDto(e))
                .collect(Collectors.toList());
        return ResponseEntity.ok(estoqueDtos);
    }

    public ResponseEntity<EstoqueDto> adicionar(EstoqueDto dto, Long idemplogada, UriComponentsBuilder builder) {
        utilsServices.validaEmpresaLogada(idemplogada);
        Estoque e = repository.save(new Estoque(dto));
        URI uri = builder.path("/estoque/listar/{id}").buildAndExpand(e.getCdEstoque()).toUri();
        return ResponseEntity.created(uri).build();
    }

    public ResponseEntity<EstoqueDto> alterar(EstoqueDto dto) {
        Estoque e=repository.save(new Estoque(dto));
        return ResponseEntity.ok(new EstoqueDto(e));
    }

    public ResponseEntity<List<ProdutoDto>> listarProdutoEstoque(Long idestoque) {
        List<ProdutoEstoque> produtos= produtoEstoqueRepository.listarProdutos(idestoque);
        List<ProdutoDto>lstp = produtos.stream().map(pe -> new ProdutoDto(pe.getProduto())).collect(Collectors.toList());
        return ResponseEntity.ok(lstp);
    }
}
