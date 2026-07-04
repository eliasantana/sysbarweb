package com.api.sysbarweb.dto;

import com.api.sysbarweb.model.Cargo;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record CargoDto(
        @JsonProperty("cdcargo")
         Long cdCargo,
        @JsonProperty("dscargo")
        String dsCargo,
        @JsonProperty("vlbruto")
         BigDecimal vlSalarioBruto,
        @JsonProperty("vlliquido")
         BigDecimal vlSalarioLiquido,
        @JsonProperty("vlinss")
         BigDecimal vlInss,
        @JsonProperty("vlfgts")
         BigDecimal vlFgts,
        @JsonProperty("snativo")
         String snAtivo) {

        public CargoDto (Cargo cargo){
           this (cargo.getCdCargo(), cargo.getDsCargo(), cargo.getVlSalarioBruto(), cargo.getVlSalarioLiquido(), cargo.getVlInss(), cargo.getVlFgts(), cargo.getSnAtivo());
        }

}
