package com.api.sysbarweb.projections;

import java.math.BigDecimal;

public interface TxEstatisticaProjection {
    //qtd_mesa, cd_empresa, qtd_ocupada, percent_ocupada, qtd_livre, percent_livre
    Integer getQtdMesa();
    Integer getCdEmpresa();
    Integer getQtdOcupada();
    BigDecimal getPercentOcupada();
    Integer getQtdLivre();
    BigDecimal getPercentLivre();

}
