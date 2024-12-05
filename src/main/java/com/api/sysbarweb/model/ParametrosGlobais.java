package com.api.sysbarweb.model;

import jakarta.persistence.*;

@Entity
@Table(name = "parametrosGlobais")
public class ParametrosGlobais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdChave;
    private String chave;

    private String dsChave;
    private String valor;

    public ParametrosGlobais(){

    }

    public Long getCdChave() {
        return cdChave;
    }

    public void setCdChave(Long cdChave) {
        this.cdChave = cdChave;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setDsChave(String dsChave) {
        this.dsChave = dsChave;
    }

    public String getDsChave() {
        return dsChave;
    }
}
