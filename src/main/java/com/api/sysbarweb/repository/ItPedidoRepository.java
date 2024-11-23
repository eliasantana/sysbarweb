package com.api.sysbarweb.repository;

import com.api.sysbarweb.model.ItPedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItPedidoRepository extends CrudRepository<ItPedido, Long> {

    @Query( value = " SELECT it.* FROM it_pedido it, pedido p  " +
            " where it.cd_pedido = p.cd_pedido  " +
            " and p.cd_empresa =:idemplogada " +
            " and p.cd_pedido =:idpedido ", nativeQuery = true)
    List<ItPedido> localizar(Long idemplogada, Long idpedido);

    @Query( value = " SELECT it.* FROM it_pedido it, pedido p  " +
            " where it.cd_pedido = p.cd_pedido  " +
            " and p.cd_empresa =:idemplogada " +
            " and p.cd_pedido =:idpedido " +
            " and it.cd_it_pedido =:iditpedido  ", nativeQuery = true)
    List<ItPedido> localizarItemDoPedido(Long idemplogada, Long idpedido, Long iditpedido);

}
