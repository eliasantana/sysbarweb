package com.api.sysbarweb.dto;

import com.api.sysbarweb.model.Produto;

import java.time.LocalDate;

public record ProdutoDto(
         Long cdProduto,
         String cdNCM,
         String cdInterno,
         String dsProduto,
         String tipo,
         LocalDate dtInclusao,
         String snAtivo

){
    public ProdutoDto(Produto p){
        this(   p.getCdProduto(),
                p.getCdNCM(),
                p.getCdInterno(),
                p.getDsProduto(),
                p.getTipo(),
                p.getDtInclusao(),
                p.getSnAtivo()
        );
    }
}
