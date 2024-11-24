package com.api.sysbarweb.repository;

import com.api.sysbarweb.model.ParametrosGlobais;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametrosGlobaisRepository extends CrudRepository<ParametrosGlobais, Long> {
    @Query(value = "select * from parametros_globais where chave=:chave", nativeQuery = true)
    ParametrosGlobais getChave(String chave);
}
