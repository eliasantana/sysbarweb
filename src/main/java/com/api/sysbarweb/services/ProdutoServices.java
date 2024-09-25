package com.api.sysbarweb.services;

import com.api.sysbarweb.dto.ProdutoDto;
import com.api.sysbarweb.exception.ProdutoException;
import com.api.sysbarweb.model.Produto;
import com.api.sysbarweb.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoServices {
    @Autowired
    ProdutoRepository repository;

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
}
