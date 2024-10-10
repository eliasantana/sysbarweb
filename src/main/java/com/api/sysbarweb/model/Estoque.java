package com.api.sysbarweb.model;

import com.api.sysbarweb.dto.EstoqueDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "estoque")
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdEstoque;
    @NotNull(message = "O Campo Descrição é obrigatório!")
    private  String dsEstoque;
    private LocalDate dtInclusao;
    @ManyToOne
    @JoinColumn(name = "cd_empresa")
    private Empresa empresa;

    public Estoque(){

    }
    public Estoque(EstoqueDto dto){
        this.cdEstoque = dto.cdEstoque();
        this.dsEstoque = dto.dsEstoque();
        this.dtInclusao = dto.dtInclusao();
        this.empresa = dto.empresa();
    }

    public Long getCdEstoque() {
        return cdEstoque;
    }

    public void setCdEstoque(Long cdEstoque) {
        this.cdEstoque = cdEstoque;
    }

    public String getDsEstoque() {
        return dsEstoque;
    }

    public void setDsEstoque(String dsEstoque) {
        this.dsEstoque = dsEstoque;
    }

    public LocalDate getDtInclusao() {
        return dtInclusao;
    }

    public void setDtInclusao(LocalDate dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
