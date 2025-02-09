package com.api.sysbarweb.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "movimentacao")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdMovimentacao;
    private Long cdProdutoEstoque;
    private String tpMovimentacao;
    private Long cdProduto;
    private int qtd;
    private String dsProduto;
    private LocalDate dtInclusao;
    private Long cdEstoque;
    private String cdUsuarioExclusao;
    private Long cdPedido;

    public Movimentacao(){
        this.dtInclusao = LocalDate.now();
    }

    public Long getCdMovimentacao() {
        return cdMovimentacao;
    }

    public void setCdMovimentacao(Long cdMovimentacao) {
        this.cdMovimentacao = cdMovimentacao;
    }

    public Long getCdProdutoEstoque() {
        return cdProdutoEstoque;
    }

    public void setCdProdutoEstoque(Long cdProdutoEstoque) {
        this.cdProdutoEstoque = cdProdutoEstoque;
    }

    public String getTpMovimentacao() {
        return tpMovimentacao;
    }

    public void setTpMovimentacao(String tpMovimentacao) {
        this.tpMovimentacao = tpMovimentacao;
    }

    public Long getCdProduto() {
        return cdProduto;
    }

    public void setCdProduto(Long cdProduto) {
        this.cdProduto = cdProduto;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public String getDsProduto() {
        return dsProduto;
    }

    public void setDsProduto(String dsProduto) {
        this.dsProduto = dsProduto;
    }

    public LocalDate getDtInclusao() {
        return dtInclusao;
    }

    public void setDtInclusao(LocalDate dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    public Long getCdEstoque() {
        return cdEstoque;
    }

    public void setCdEstoque(Long cdEstoque) {
        this.cdEstoque = cdEstoque;
    }

    public String getCdUsuarioExclusao() {
        return cdUsuarioExclusao;
    }

    public void setCdUsuarioExclusao(String cdUsuarioExclusao) {
        this.cdUsuarioExclusao = cdUsuarioExclusao;
    }

    public Long getCdPedido() {
        return cdPedido;
    }

    public void setCdPedido(Long cdPedido) {
        this.cdPedido = cdPedido;
    }
}
