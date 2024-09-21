package com.api.sysbarweb.repository;

import com.api.sysbarweb.model.Funcionario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioResponsitory extends CrudRepository<Funcionario, Long> {
   @Query(value = "select * from funcionario where cd_empresa =:cdempresa",nativeQuery = true)
    List<Funcionario> listar(Long cdempresa);
}
