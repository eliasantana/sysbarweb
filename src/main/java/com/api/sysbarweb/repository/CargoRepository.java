package com.api.sysbarweb.repository;

import com.api.sysbarweb.dto.CargoDto;
import com.api.sysbarweb.model.Cargo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Long> {
    @Query(value = "select * from cargo where sn_ativo='S'",nativeQuery = true)
    List<Cargo> listarTodos();

    @Query(value = "select * from cargo where ds_cargo=:cargo and sn_ativo='S'",nativeQuery = true)
    Optional<Cargo> existeCargo(String cargo);

    @Query(value = "select * from cargo where cd_cargo=:cdcargo and sn_ativo='S'",nativeQuery = true)
    Optional<Cargo> localizar(Long cdcargo);
}
