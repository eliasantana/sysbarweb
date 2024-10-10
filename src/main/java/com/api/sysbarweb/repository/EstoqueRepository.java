package com.api.sysbarweb.repository;

import com.api.sysbarweb.model.Estoque;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstoqueRepository extends CrudRepository<Estoque, Long> {
    @Query(value = "Select * from estoque where cd_empresa=:idemplogada",nativeQuery = true)
    List<Estoque> listar(Long idemplogada);

    @Query(value = "Select * from estoque where cd_empresa=:idemplogada and cd_estoque=:idestoque and sn_ativo='S'",nativeQuery = true)
    Optional<Estoque> localizar(Long idemplogada, Long idestoque);
}
