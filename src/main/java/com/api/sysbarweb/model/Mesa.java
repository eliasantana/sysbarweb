package com.api.sysbarweb.model;

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

    public Mesa(){

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
}
