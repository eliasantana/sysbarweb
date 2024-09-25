package com.api.sysbarweb.repository;

import com.api.sysbarweb.model.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long>{


    @Query(value ="select * from produto where ds_produto=:dsproduto",nativeQuery = true)
    Optional<Produto> localizarProdutoPorDescricao(String dsproduto);
    @Query(value ="select * from produto where sn_ativo='S'",nativeQuery = true)
    List<Produto> listarTodos();
}
