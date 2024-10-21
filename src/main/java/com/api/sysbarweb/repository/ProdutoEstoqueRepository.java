package com.api.sysbarweb.repository;

import com.api.sysbarweb.model.ProdutoEstoque;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoEstoqueRepository  extends CrudRepository<ProdutoEstoque, Long> {


    @Query(value =  "select * from produto_estoque where cd_estoque=:idestoque and sn_ativo='S'", nativeQuery = true)
    List<ProdutoEstoque> listarProdutos(Long idestoque);

    @Query(value =  "select * from produto_estoque where cd_estoque=:cdestoque and cd_produto=:cdproduto", nativeQuery = true)
    Optional<ProdutoEstoque> validaProdutoEstoque(Long cdestoque, Long cdproduto);
    }
