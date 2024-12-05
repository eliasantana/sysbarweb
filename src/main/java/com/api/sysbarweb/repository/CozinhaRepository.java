package com.api.sysbarweb.repository;


import com.api.sysbarweb.model.Cozinha;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CozinhaRepository extends CrudRepository<Cozinha, Long> {
    @Query(value = "select c.* from mesa m,  cozinha c " +
        " where m.nr_mesa = c.nr_mesa " +
        " and m.cd_empresa =:idemplogada " +
        " and m.cd_funcionario =:idfuncionario", nativeQuery = true)
    List<Cozinha> statusCozinha(Long idemplogada, Long idfuncionario);

    @Query(value = "select * from cozinha where cd_cozinha=:idcozinha", nativeQuery = true)
    Optional<Cozinha> localizarPrato(Long idcozinha);

}
