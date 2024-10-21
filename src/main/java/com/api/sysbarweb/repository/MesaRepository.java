package com.api.sysbarweb.repository;

import com.api.sysbarweb.model.Mesa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MesaRepository extends CrudRepository<Mesa, Long> {

    @Query(value = "select * from mesa where cd_empresa=:idempresa", nativeQuery = true)
    List<Mesa> listarTodas(Long idempresa);
    @Query(value = "select * from mesa where cd_empresa=:idemplogada and nr_mesa=:nrmesa", nativeQuery = true)
    Optional<Mesa> existeMesa(Long idemplogada, int nrmesa);
    @Query(value = "select * from mesa where cd_mesa=:cdmesa", nativeQuery = true)
    Optional<Mesa> existeMesa(Long cdmesa);
    @Query(value = "select * from mesa where cd_empresa=:idemplogada and cd_funcionario=:idfuncionario", nativeQuery = true)
    List<Mesa> listarMesaGarcom(Long idemplogada, Long idfuncionario);
}
