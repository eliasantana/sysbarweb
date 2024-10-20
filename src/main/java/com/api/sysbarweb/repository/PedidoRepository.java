package com.api.sysbarweb.repository;

import com.api.sysbarweb.model.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {


    @Query(value = " select p.* from pedido p, mesa m " +
                   " where p.cd_mesa = m.cd_mesa and m.status = 'O'" +
                   " and p.cd_empresa=:idemplogada ", nativeQuery = true)
    List<Pedido> listar(Long idemplogada);
}