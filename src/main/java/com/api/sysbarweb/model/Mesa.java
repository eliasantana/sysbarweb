package com.api.sysbarweb.model;

import com.api.sysbarweb.dto.MesaDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "mesa")
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdMesa;
    private  int nrMesa;
    @ManyToOne
    @JoinColumn(name = "cd_empresa")
    private Empresa empresa;
    @ManyToOne
    @JoinColumn(name = "cd_funcionario")
    private Funcionario funcionario;

    private String status;

    public Mesa(){

    }

    public Mesa(MesaDto dto){
        this.cdMesa = dto.cdMesa();
        this.nrMesa = dto.nrMesa();
        this.empresa = dto.empresa();
        this.funcionario = dto.funcionario();
        this.status = dto.status();
    }

    public Long getCdMesa() {
        return cdMesa;
    }

    public void setCdMesa(Long cdMesa) {
        this.cdMesa = cdMesa;
    }

    public int getNrMesa() {
        return nrMesa;
    }

    public void setNrMesa(int nrMesa) {
        this.nrMesa = nrMesa;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
