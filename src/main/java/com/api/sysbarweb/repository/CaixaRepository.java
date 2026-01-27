package com.api.sysbarweb.repository;

import com.api.sysbarweb.dto.CaixaDto;
import com.api.sysbarweb.model.Caixa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaixaRepository extends CrudRepository<Caixa, Long> {
    //Retorna o caixa da data atual ou data atual + 1 dia
    @Query(value = " select * from caixa " +
                   " where cd_empresa=:idemplogada " +
                   " and cd_funcionario =:idfuncionario " +
                   " and date_format(dt_abertura,'%d/%m/%Y') = date_format(curdate(),'%d/%m/%Y') " +
                   " or date_format(dt_abertura,'%d/%m/%Y') = date_format(DATE_SUB(curdate(), INTERVAL 1 DAY ),'%d/%m/%Y')", nativeQuery = true)
    Caixa localizaCaixa(Long idemplogada, Long idfuncionario);

    @Query(value = "SELECT * FROM caixa where status = 'A'",nativeQuery = true)
    List<Caixa> retornaTodosOsCaixas();

    //Retorna caixa específico pelo id do Caixa, Funcionário e Empresa logada
    @Query(value = " select * from caixa " +
            " where cd_empresa=:idemplogada " +
            " and cd_funcionario =:idfuncionario " +
            " and cd_caixa =:idCaixa" , nativeQuery = true)
    Caixa localizaCaixa(Long idemplogada, Long idfuncionario, Long idCaixa);
}
