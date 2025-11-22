package com.api.sysbarweb.repository;

import com.api.sysbarweb.model.Caixa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaixaRepository extends CrudRepository<Caixa, Long> {
    //Retorna o caixa da data atual ou data atual + 1 dia
    @Query(value = " select * from caixa " +
                   " where cd_empresa=:idemplogada " +
                   " and cd_funcionario =:idfuncionario " +
                   " and date_format(dt_abertura,'%d/%m/%Y') = date_format(curdate(),'%d/%m/%Y') " +
                   " or date_format(dt_abertura,'%d/%m/%Y') = date_format(DATE_SUB(curdate(), INTERVAL 1 DAY ),'%d/%m/%Y')", nativeQuery = true)
    Caixa localizaCaixa(Long idemplogada, Long idfuncionario);
}
