package com.api.sysbarweb.services;

import com.api.sysbarweb.dto.ProdutoDto;
import com.api.sysbarweb.dto.ProdutoEstoqueDto;
import com.api.sysbarweb.exception.ProdutoEstoqueException;
import com.api.sysbarweb.exception.ProdutoException;
import com.api.sysbarweb.model.*;
import com.api.sysbarweb.repository.EstoqueRepository;
import com.api.sysbarweb.repository.ProdutoEstoqueRepository;
import com.api.sysbarweb.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoServices {
    @Autowired
    ProdutoRepository repository;
    @Autowired
    ProdutoEstoqueRepository produtoEstoqueRepository;
    @Autowired
    UtilsServices utilsServices;
    @Autowired
    EstoqueRepository estoqueRepository;

    @Autowired
    MovimentacaoServices movimentacaoServices;


    public ResponseEntity<ProdutoServices> adicionar(ProdutoDto dto, UriComponentsBuilder builder) {
        Optional<Produto> produtoLocalizado = repository.localizarProdutoPorDescricao(dto.dsProduto());
        Produto produtoSalvo = new Produto();
        if(produtoLocalizado.isEmpty()){
            produtoSalvo=repository.save(new Produto(dto));
        }else{
            throw new ProdutoException("O Produto informado já existe!");
        }
        URI uri = builder.path("/produto/listar/").buildAndExpand(new ProdutoDto(produtoSalvo).cdProduto()).toUri();
        return ResponseEntity.created(uri).build();
    }

    public ResponseEntity<ProdutoDto> localizar(Long idproduto) {
        Optional<Produto> produtoLocalizado = repository.findById(idproduto);
        if (produtoLocalizado.isEmpty()){
            throw new ProdutoException("O produto Informado não foi localizado!");
        }
        return ResponseEntity.ok(new ProdutoDto(produtoLocalizado.get()));
    }

    /**
     * Alterar o método para trazer apenas os produtos da empresa logada!
     * Por enquanto retornando todos os produtos  de todas as empresas
     * */
    public ResponseEntity<List<ProdutoDto>> listarTodos() {
        List<Produto> produtos  = repository.listarTodos();
        if (produtos.isEmpty()){
            throw new ProdutoException("Nenhum Produto Localizado!");
        }
        List<ProdutoDto> produtoDtos =produtos.stream()
                .map(p -> new ProdutoDto(p))
                .collect(Collectors.toList());
        return ResponseEntity.ok(produtoDtos);
    }

    public ResponseEntity<ProdutoDto> excluir(Long idproduto) {
        Optional<Produto> p =repository.findById(idproduto);
        Produto produtoSalvo = new Produto();
        if (p.isEmpty()){
            throw new ProdutoException("O Produto informado não foi localizado!");
        }else{
            p.get().setSnAtivo("N");
            produtoSalvo = repository.save(p.get());
        }
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<ProdutoDto> alterar(ProdutoDto dto) {
        Produto p = new Produto(dto);
        System.out.println(p.toString());
        Produto produtoSalvo = repository.save(p);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<ProdutoEstoqueDto> adicionarProdutoEstoque(Long idemplogada, ProdutoEstoqueDto dto,UriComponentsBuilder builder) {
        if(dto.qtd()<=0){
            throw new ProdutoEstoqueException("O produto não pode ser incluído com quantidade zerada!");
        }
        Optional<Empresa> emplogada = utilsServices.validaEmpresaLogada(idemplogada);
        Optional<Estoque> estoque = utilsServices.validaEstoque(dto.estoque().getCdEstoque(),idemplogada);
        Optional<Produto> produto = utilsServices.validaProduto(dto.produto().getCdProduto());
        Optional<ProdutoEstoque> produtoEstoqueLocalizado = utilsServices.validaProdutoEstoque(estoque.get().getCdEstoque(), produto.get().getCdProduto());
        URI uri = null;
        if (produtoEstoqueLocalizado.isEmpty()){
            ProdutoEstoque pe = new ProdutoEstoque(dto);
            if(pe.getDtInclusao()==null){
                pe.setDtInclusao(LocalDate.now());
            }
            pe.setEstoque(estoque.get());
            pe.setProduto(produto.get());
            ProdutoEstoque produtoEstoqueSalvo =  produtoEstoqueRepository.save(pe);
            movimentacaoServices.adicionaMovimentacao(produtoEstoqueSalvo, dto.qtd(), "E");
            uri = builder.path("produt/estoque/lsitar/{id}").buildAndExpand(produtoEstoqueSalvo.getProduto().getCdProduto()).toUri();
        }else{
            ProdutoEstoque produtoEstoqueAtual = produtoEstoqueLocalizado.get();
            produtoEstoqueAtual.setQtd(produtoEstoqueAtual.getQtd() + dto.qtd());
            ProdutoEstoque produtoEstoqueSalvo =  produtoEstoqueRepository.save(produtoEstoqueAtual);
            //produtoEstoqueSalvo.setQtd(dto.qtd()); //Seta novamente a quatidade solicitada para registrar a quantidade correna na movimentação
            movimentacaoServices.adicionaMovimentacao(produtoEstoqueSalvo, dto.qtd(),"E");
            uri = builder.path("produt/estoque/lsitar/{id}").buildAndExpand(produtoEstoqueSalvo.getProduto().getCdProduto()).toUri();
        }
        return ResponseEntity.created(uri).build();
    }

}
