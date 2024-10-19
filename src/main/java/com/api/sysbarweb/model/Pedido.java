package com.api.sysbarweb.model;

import com.api.sysbarweb.dto.PedidoDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdPedido;
    private LocalDate dtInclusao;
    @NotNull
    private Long cdEmpresa;
    @NotNull
    private Long cdFuncionario;
    private BigDecimal totalPedido;
    @NotNull
    private Long cdMesa;

    public Pedido(){
        this.dtInclusao = LocalDate.now();
        this.totalPedido = BigDecimal.ZERO;
    }

    public Pedido(PedidoDto dto){
        this.cdPedido = dto.cdPedido();
        this.dtInclusao = dto.dtInclusao();
        this.totalPedido = dto.totalPedido();

    }

    public Long getCdPedido() {
        return cdPedido;
    }

    public void setCdPedido(Long cdPedido) {
        this.cdPedido = cdPedido;
    }

    public LocalDate getDtInclusao() {
        return dtInclusao;
    }

    public void setDtInclusao(LocalDate dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    public Long getCdEmpresa() {
        return cdEmpresa;
    }

    public void setCdEmpresa(Long cdEmpresa) {
        this.cdEmpresa = cdEmpresa;
    }

    public Long getCdFuncionario() {
        return cdFuncionario;
    }

    public void setCdFuncionario(Long cdFuncionario) {
        this.cdFuncionario = cdFuncionario;
    }

    public BigDecimal getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(BigDecimal totalPedido) {
        this.totalPedido = totalPedido;
    }

    public void setCdMesa(Long cdMesa) {
        this.cdMesa = cdMesa;
    }

    public Long getCdMesa() {
        return cdMesa;
    }
}
