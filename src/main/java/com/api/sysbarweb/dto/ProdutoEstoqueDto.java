package com.api.sysbarweb.dto;

import com.api.sysbarweb.model.Empresa;
import com.api.sysbarweb.model.Estoque;
import com.api.sysbarweb.model.Produto;
import com.api.sysbarweb.model.ProdutoEstoque;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProdutoEstoqueDto(
Long cdProdutoEstoque,
Produto produto,
Estoque estoque,
int qtd,
int qtdMax,
int qtdMin,
String snAtivo,
LocalDate dtInclusao,
BigDecimal vlVenda

) {
    public ProdutoEstoqueDto(ProdutoEstoque pe){
        this (pe.getCdProdutoEstoque(),
                pe.getProduto(),
                pe.getEstoque(),
                pe.getQtd(),
                pe.getQtdMax(),
                pe.getQtdMin(),
                pe.getSnAtivo(),
                pe.getDtInclusao(),
                pe.getVlVenda());
    }
}
