package com.api.sysbarweb.model;

import com.api.sysbarweb.dto.CaixaDto;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "caixa")
public class Caixa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdCaixa;
    private LocalDate dtAbertura;
    private LocalDate dtFechamento;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_funcionario")
    private Funcionario funcionario;
    @ManyToOne
    @JoinColumn(name = "cd_empresa")
    private Empresa empresa;
    private BigDecimal saldoInicial;
    private BigDecimal saldoFinal;

    private String status; //F - FEchado A-Aberto
    public Caixa(){

    }
    public Caixa(CaixaDto dto){
       // this.cdCaixa = dto.cdCaixa();
        this.dtAbertura = LocalDate.now();
        this.dtFechamento = dto.dtFechamento();
        this.funcionario = dto.funcionario();
        this.empresa = dto.empresa();
        this.saldoInicial = dto.saldoInicial();
        this.saldoFinal = dto.saldoFinal();

    }

    public Long getCdCaixa() {
        return cdCaixa;
    }

    public void setCdCaixa(Long cdCaixa) {
        this.cdCaixa = cdCaixa;
    }

    public LocalDate getDtAbertura() {
        return dtAbertura;
    }

    public void setDtAbertura(LocalDate dtAbertura) {
        this.dtAbertura = dtAbertura;
    }

    public LocalDate getDtFechamento() {
        return dtFechamento;
    }

    public void setDtFechamento(LocalDate dtFechamento) {
        this.dtFechamento = dtFechamento;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public BigDecimal getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(BigDecimal saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
