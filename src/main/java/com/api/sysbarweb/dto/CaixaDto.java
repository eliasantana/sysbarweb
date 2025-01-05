package com.api.sysbarweb.dto;

import com.api.sysbarweb.model.Caixa;
import com.api.sysbarweb.model.Empresa;
import com.api.sysbarweb.model.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CaixaDto(
         Long cdCaixa,
         LocalDate dtAbertura,
         LocalDate dtFechamento,
         Funcionario funcionario,
         Empresa empresa,
         BigDecimal saldoInicial,
         BigDecimal saldoFinal,
         String status

) {
    public CaixaDto(Caixa caixa){
        this(caixa.getCdCaixa(),
                caixa.getDtAbertura(),
                caixa.getDtFechamento(),
                caixa.getFuncionario(),
                caixa.getEmpresa(),
                caixa.getSaldoInicial(),
                caixa.getSaldoFinal(),
                caixa.getStatus());
    }
}
