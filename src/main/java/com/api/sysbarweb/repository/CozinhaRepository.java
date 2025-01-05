package com.api.sysbarweb.repository;


import com.api.sysbarweb.model.Cozinha;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CozinhaRepository extends CrudRepository<Cozinha, Long> {
    @Query(value = " select * from  cozinha c                                                " +
            " where c.cd_empresa =:idemplogada                                               " +
            " and c.cd_funcionario =:idfuncionario                                           " +
            " and date_format(c.hora_solicitacao,'%d%m%Y') >=date_format(curdate(),'%d%m%Y') " +
            " and cd_pedido =:idpedido "
            , nativeQuery = true)
    List<Cozinha> statusCozinha(Long idemplogada, Long idfuncionario, long idpedido);

    @Query(value = "select * from cozinha where cd_cozinha=:idcozinha", nativeQuery = true)
    Optional<Cozinha> localizarPrato(Long idcozinha);

}
