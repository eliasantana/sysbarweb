package com.api.sysbarweb.model;

import com.api.sysbarweb.dto.ItPedidoDto;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "itPedido")
public class ItPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdItPedido;
    private LocalDate dtInclusao;
    @ManyToOne
    @JoinColumn(name = "cd_pedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "cd_produto")
    private Produto produto;

    private int qtd;

    private BigDecimal vlUnit;

    public ItPedido(){
        this.dtInclusao = LocalDate.now();
    }

    public ItPedido(ItPedidoDto dto){
        this.cdItPedido = dto.cdItPedido();
        this.dtInclusao = dto.dtInclusao();
        this.pedido = dto.pedido();
        this.produto = dto.produto();
        this.qtd = dto.qtd();
        this.vlUnit = dto.vlUnit();
    }

    public Long getCdItPedido() {
        return cdItPedido;
    }

    public void setCdItPedido(Long cdItPedido) {
        this.cdItPedido = cdItPedido;
    }

    public LocalDate getDtInclusao() {
        return dtInclusao;
    }

    public void setDtInclusao(LocalDate dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public int getQtd() {
        return qtd;
    }

    public void setVlUnit(BigDecimal vlUnit) {
        this.vlUnit = vlUnit;
    }

    public BigDecimal getVlUnit() {
        return vlUnit;
    }
}
