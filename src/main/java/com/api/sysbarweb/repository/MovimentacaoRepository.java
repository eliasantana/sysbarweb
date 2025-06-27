package com.api.sysbarweb.repository;

import com.api.sysbarweb.model.Movimentacao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovimentacaoRepository extends CrudRepository<Movimentacao, Long> {
    @Query(value = " select m.* from movimentacao m,  it_pedido it " +
                   " where m.cd_pedido = it.cd_pedido " +
                   " and it.cd_it_pedido =:cditpedido " +
                   " and m.cd_movimentacao in ( " +
                   "                            select max(cd_movimentacao) from movimentacao " +
                   "                                 where cd_pedido =:cdpedido and cd_produto=:idproduto " +
                   "                                 and qtd =:qtd) " , nativeQuery = true)
    Optional<Movimentacao> localizarMovimentacao(Long cdpedido, Long idproduto, int qtd, Long cditpedido);
}
