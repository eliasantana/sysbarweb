package com.api.sysbarweb.model;

import com.api.sysbarweb.dto.ItPedidoDto;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "itPedido")
public class ItPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdItPedido;
    private LocalDate dtInclusao;
    private Pedido pedido;
    private Produto produto;

    public ItPedido(){
        this.dtInclusao = LocalDate.now();
    }

    public ItPedido(ItPedidoDto dto){
        this.cdItPedido = dto.cdItPedido();
        this.dtInclusao = dto.dtInclusao();
        this.pedido = dto.pedido();
        this.produto = dto.produto();
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
}
