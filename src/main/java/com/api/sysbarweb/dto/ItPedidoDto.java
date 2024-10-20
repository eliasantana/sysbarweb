package com.api.sysbarweb.dto;

import com.api.sysbarweb.model.ItPedido;
import com.api.sysbarweb.model.Pedido;
import com.api.sysbarweb.model.Produto;

import java.time.LocalDate;

public record ItPedidoDto(
         Long cdItPedido,
         LocalDate dtInclusao,
         Pedido pedido,
         Produto produto
) {
    public ItPedidoDto(ItPedido i){
        this(i.getCdItPedido(), i.getDtInclusao(), i.getPedido(), i.getProduto());
    }
}
