package com.api.sysbarweb.repository;

import com.api.sysbarweb.model.Mesa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MesaRepository extends CrudRepository<Mesa, Long> {

    @Query(value = "select * from mesa where cd_empresa=:idempresa", nativeQuery = true)
    List<Mesa> listarTodas(Long idempresa);
}
