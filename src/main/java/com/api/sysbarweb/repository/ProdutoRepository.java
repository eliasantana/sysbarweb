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
    @Query(value ="select * from produto where cd_produto=:cdproduto and sn_ativo='S'",nativeQuery = true)
    Optional<Produto> localizar(Long cdproduto);

    @Query(value ="Select p.* from produto p, produto_estoque pe, estoque e " +
                  " where p.cd_produto = pe.cd_produto " +
                  " and pe.cd_estoque = e.cd_estoque " +
                  " and p.sn_ativo = 'S' " +
                  " and e.cd_empresa =:cdemplogada  " +
                  " and pe.qtd > 0",nativeQuery = true)
    List<Produto> listarTodosOsProdutosEmpLogada(Long cdemplogada);
}
