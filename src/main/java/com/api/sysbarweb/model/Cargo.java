package com.api.sysbarweb.model;

import com.api.sysbarweb.dto.CargoDto;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Cargo")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdCargo;
    private String dsCargo;
    private BigDecimal vlSalarioBruto;
    private BigDecimal vlSalarioLiquido;
    private BigDecimal vlInss;
    private BigDecimal vlFgts;

    private String snAtivo;

    public Cargo(CargoDto dto){
        this.cdCargo = dto.cdCargo();
        this.dsCargo = dto.dsCargo();
        this.vlSalarioBruto = dto.vlSalarioBruto();
        this.vlSalarioLiquido = dto.vlSalarioLiquido();
        this.vlInss = dto.vlInss();
        this.vlFgts = dto.vlFgts();
        this.snAtivo = dto.snAtivo().toUpperCase();
    }

    Cargo(){}

    public Long getCdCargo() {
        return cdCargo;
    }

    public void setCdCargo(Long cdCargo) {
        this.cdCargo = cdCargo;
    }

    public String getDsCargo() {
        return dsCargo;
    }

    public void setDsCargo(String dsCargo) {
        this.dsCargo = dsCargo;
    }

    public BigDecimal getVlSalarioBruto() {
        return vlSalarioBruto;
    }

    public void setVlSalarioBruto(BigDecimal vlSalarioBruto) {
        this.vlSalarioBruto = vlSalarioBruto;
    }

    public BigDecimal getVlSalarioLiquido() {
        return vlSalarioLiquido;
    }

    public void setVlSalarioLiquido(BigDecimal vlSalarioLiquido) {
        this.vlSalarioLiquido = vlSalarioLiquido;
    }

    public BigDecimal getVlInss() {
        return vlInss;
    }

    public void setVlInss(BigDecimal vlInss) {
        this.vlInss = vlInss;
    }

    public BigDecimal getVlFgts() {
        return vlFgts;
    }

    public void setVlFgts(BigDecimal vlFgts) {
        this.vlFgts = vlFgts;
    }

    public void setSnAtivo(String snAtivo) {
        this.snAtivo = snAtivo;
    }

    public String getSnAtivo() {
        return snAtivo;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "cdCargo=" + cdCargo +
                ", dsCargo='" + dsCargo + '\'' +
                ", vlSalarioBruto=" + vlSalarioBruto +
                ", vlSalarioLiquido=" + vlSalarioLiquido +
                ", vlInss=" + vlInss +
                ", vlFgts=" + vlFgts +
                '}';
    }
}
