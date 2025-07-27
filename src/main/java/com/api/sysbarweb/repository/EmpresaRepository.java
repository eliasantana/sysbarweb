package com.api.sysbarweb.repository;

import com.api.sysbarweb.dto.EmpresaDto;
import com.api.sysbarweb.model.Empresa;
import com.api.sysbarweb.projections.TxEstatisticaProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EmpresaRepository extends CrudRepository<Empresa, Long> {
    @Query(value = "select * from empresa where cd_empresa =:cdempresa and sn_ativo='S'", nativeQuery = true)
    Optional<Empresa> getEmpresa(Long cdempresa);
    @Query(value = "select * from empresa where cnpj =:cnpj and sn_ativo='S'", nativeQuery = true)
    List <Optional<Empresa>> existEmpresa(String cnpj);

    @Query(value = "select * from empresa", nativeQuery = true)
    List<Empresa> listarTodas();

    @Query(value = "select * from v_tx_ocupacao where cd_empresa=:cdempresa", nativeQuery = true)
    TxEstatisticaProjection getStatistica(Long cdempresa);
}
