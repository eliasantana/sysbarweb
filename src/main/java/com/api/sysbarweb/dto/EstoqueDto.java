package com.api.sysbarweb.dto;

import com.api.sysbarweb.model.Empresa;
import com.api.sysbarweb.model.Estoque;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EstoqueDto(
        Long cdEstoque,
        String dsEstoque,
        LocalDate dtInclusao,
        Empresa empresa
) {
    public EstoqueDto(Estoque e){
        this(e.getCdEstoque(), e.getDsEstoque(), e.getDtInclusao(), e.getEmpresa());
    }
}
