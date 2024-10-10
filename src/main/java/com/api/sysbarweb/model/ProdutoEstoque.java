package com.api.sysbarweb.model;

import com.api.sysbarweb.dto.ProdutoDto;
import com.api.sysbarweb.dto.ProdutoEstoqueDto;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "produto_estoque")
public class ProdutoEstoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdProdutoEstoque;
    @ManyToOne
    @JoinColumn(name = "cd_produto")
    private Produto produto;
    @ManyToOne
    @JoinColumn(name = "cd_estoque")
    private Estoque estoque;
    private int qtd;
    private int qtdMax;
    private int qtdMin;
    private String snAtivo;
    private LocalDate dtInclusao;
    private BigDecimal vlVenda;

    public ProdutoEstoque(){

    }

    public ProdutoEstoque(ProdutoEstoqueDto dto){
        this.cdProdutoEstoque = dto.cdProdutoEstoque();
        this.produto = dto.produto();
        this.estoque=dto.estoque();
        this.qtd = dto.qtd();
        this.qtdMax = dto.qtdMax();
        this.qtdMin = dto.qtdMin();
        this.snAtivo = dto.snAtivo();
        this.dtInclusao = getDtInclusao();
        this.vlVenda = dto.vlVenda();
    }


    public Long getCdProdutoEstoque() {
        return cdProdutoEstoque;
    }

    public void setCdProdutoEstoque(Long cdProdutoEstoque) {
        this.cdProdutoEstoque = cdProdutoEstoque;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public int getQtdMax() {
        return qtdMax;
    }

    public void setQtdMax(int qtdMax) {
        this.qtdMax = qtdMax;
    }

    public int getQtdMin() {
        return qtdMin;
    }

    public void setQtdMin(int qtdMin) {
        this.qtdMin = qtdMin;
    }

    public String getSnAtivo() {
        return snAtivo;
    }

    public void setSnAtivo(String snAtivo) {
        this.snAtivo = snAtivo;
    }

    public LocalDate getDtInclusao() {
        return dtInclusao;
    }

    public void setDtInclusao(LocalDate dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    public BigDecimal getVlVenda() {
        return vlVenda;
    }

    public void setVlVenda(BigDecimal vlVenda) {
        this.vlVenda = vlVenda;
    }
}
