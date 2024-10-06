package com.api.sysbarweb.dto;

import com.api.sysbarweb.model.Empresa;
import com.api.sysbarweb.model.Funcionario;
import com.api.sysbarweb.model.Mesa;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record MesaDto(
         Long cdMesa,
         int nrMesa,
         Empresa empresa,

         Funcionario funcionario
) {
    public MesaDto(Mesa m){
       this(m.getCdMesa(), m.getNrMesa(), m.getEmpresa(), m.getFuncionario());
    }
    }
