package com.api.sysbarweb.repository;

import com.api.sysbarweb.model.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {


    @Query(value = " select p.* from pedido p, mesa m " +
                   " where p.cd_mesa = m.cd_mesa and m.status = 'O' and p.status_pedido <> 'F' " +
                   " and p.cd_empresa=:idemplogada ", nativeQuery = true)
    List<Pedido> listar(Long idemplogada);

    @Query(value = " select p.* from pedido p, mesa m " +
            " where p.cd_mesa = m.cd_mesa and m.status = 'O' and p.status_pedido <> 'F' " +
            " and p.cd_empresa=:idemplogada and p.cd_pedido=:idpedido ", nativeQuery = true)
    List<Pedido> listar(Long idemplogada, Long idpedido);

    @Query(value = "Select * from pedido where cd_pedido=:idpedido and cd_empresa=:idemplogada", nativeQuery = true)
    Optional<Pedido> validaPedido(Long idemplogada, Long idpedido);


    @Query(value=" select p.* from pedido p, mesa m, empresa e " +
            " where p.cd_mesa = m.cd_mesa " +
            " and p.cd_empresa = e.cd_empresa " +
            " and m.cd_mesa =:nrmesa " +
            " and e.cd_empresa =:cdemplogada " +
            " and p.status_pedido = 'A'", nativeQuery = true)
    Pedido localizaPedidoPorNumeroMesa(Long nrmesa, Long cdemplogada);
}
