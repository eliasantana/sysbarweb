package com.api.sysbarweb.repository;

import com.api.sysbarweb.model.Funcionario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioResponsitory extends CrudRepository<Funcionario, Long> {
    @Query(value = "select * from funcionario where cd_empresa =:cdempresa",nativeQuery = true)
    List<Funcionario> listar(Long cdempresa);

    @Query(value = "select * from funcionario where cd_empresa =:cdempresa and cd_funcionario=:idfuncionario",nativeQuery = true)
    List<Funcionario> localizarFuncionario(Long cdempresa, Long idfuncionario);

    @Query(value="select * from funcionario where nr_cpf=:nrCpf or nr_rg =:nrrg limit 1", nativeQuery = true)
    Optional<Funcionario> localizarFuncionario(String nrCpf, String nrrg);

    @Query(value="select * from funcionario where nr_cpf=:nrCpf limit 1", nativeQuery = true)
    Optional<Funcionario> localizarFuncionario(String nrCpf);

}
