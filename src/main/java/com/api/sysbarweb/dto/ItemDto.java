package com.api.sysbarweb.dto;

import java.math.BigDecimal;

public class ItemDto {

            private Long cdPedido;
            private Long cdItemPedido;
            private String cdProduto;
            private  String dtInclusao;
            private String dsProduto;

            private int qtd;
            private BigDecimal vlUnit;

            private BigDecimal total;

    public ItemDto(){

    }

    public String getCdProduto() {
        return cdProduto;
    }

    public void setCdProduto(String cdProduto) {
        this.cdProduto = cdProduto;
    }

    public String getDtInclusao() {
        return dtInclusao;
    }

    public void setDtInclusao(String dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public BigDecimal getVlUnit() {
        return vlUnit;
    }

    public void setVlUnit(BigDecimal vlUnit) {
        this.vlUnit = vlUnit;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Long getCdItemPedido() {
        return cdItemPedido;
    }
    public void setCdItemPedido(Long cdItemPedido) {
        this.cdItemPedido = cdItemPedido;
    }

    public void setDsProduto(String dsProduto) {
        this.dsProduto = dsProduto;
    }

    public String getDsProduto() {
        return dsProduto;
    }

    public Long getCdPedido() {
        return cdPedido;
    }

    public void setCdPedido(Long cdPedido) {
        this.cdPedido = cdPedido;
    }
}
