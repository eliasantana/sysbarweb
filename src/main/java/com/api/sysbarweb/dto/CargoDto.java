package com.api.sysbarweb.dto;

import com.api.sysbarweb.model.Cargo;

import java.math.BigDecimal;

public record CargoDto(
         Long cdCargo,
         String dsCargo,
         BigDecimal vlSalarioBruto,
         BigDecimal vlSalarioLiquido,
         BigDecimal vlInss,
         BigDecimal vlFgts,
         String snAtivo) {

        public CargoDto (Cargo cargo){
           this (cargo.getCdCargo(), cargo.getDsCargo(), cargo.getVlSalarioBruto(), cargo.getVlSalarioLiquido(), cargo.getVlInss(), cargo.getVlFgts(), cargo.getSnAtivo());
        }

}
