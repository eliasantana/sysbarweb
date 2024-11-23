package com.api.sysbarweb.dto;

import com.api.sysbarweb.model.Pedido;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PedidoDto(
    Long cdPedido,
    LocalDate dtInclusao,
    BigDecimal totalPedido,
    Long cdMesa,
    String statusPedido
) {
    public PedidoDto(Pedido p){

        this (p.getCdPedido(), p.getDtInclusao(), p.getTotalPedido(), p.getCdMesa(), p.getStatusPedido());
    }
}
