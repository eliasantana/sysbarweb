package com.api.sysbarweb.dto;

import com.api.sysbarweb.model.Cozinha;

import java.time.LocalDateTime;

public record CozinhaDto(
        Long cdCozinha,
        Long cdProduto,
        Long cdPedido,
        LocalDateTime horaSolicitacao,
        LocalDateTime horaPrepacacao,
        String tempoPreparacao,
        int qtd,
        Long cdFuncionario,
        String nmFuncionario,
        int nrMesa,
        String status,
        String observacao,
        Long cdEmpresa,
        String nmPrato
        
) {
    public CozinhaDto(Cozinha c){
        this(   c.getCdCozinha(),
                c.getCdProduto(),
                c.getCdPedido(),
                c.getHoraSolicitacao(),
                c.getHoraPrepacacao(),
                c.getTempoPreparacao(),
                c.getQtd(),
                c.getCdFuncionario(),
                c.getNmFuncionario(),
                c.getNrMesa(),
                c.getStatus(),
                c.getObservacao(),
                c.getCdEmpresa(),
                c.getNmPrato());
    }
    
}
