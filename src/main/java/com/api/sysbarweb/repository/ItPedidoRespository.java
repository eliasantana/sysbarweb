package com.api.sysbarweb.repository;

import com.api.sysbarweb.model.ItPedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItPedidoRespository  extends CrudRepository<ItPedido, Long> {

    @Query( value = " SELECT it.* FROM it_pedido it, pedido p  " +
            " where it.cd_pedido = p.cd_pedido  " +
            " and p.cd_empresa =:idemplogada " +
            " and p.cd_pedido =:idpedido ", nativeQuery = true)
    List<ItPedido> localizar(Long idemplogada, Long idpedido);
}
