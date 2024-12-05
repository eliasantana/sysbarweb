package com.api.sysbarweb.model;

import com.api.sysbarweb.dto.CozinhaDto;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "cozinha")
public class Cozinha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdCozinha;
    private Long cdProduto;
    private Long cdPedido;
    private LocalDateTime horaSolicitacao;
    private LocalDateTime horaPrepacacao;
    private String tempoPreparacao;
    private int qtd;
    private Long cdFuncionario;
    private String nmFuncionario;
    private int nrMesa;
    private String status;  // P - Pendente E - Em Preparação L - Liberado
    private String observacao;
    private Long cdEmpresa;

    private String nmPrato;

    public Cozinha(){

    }

    public Cozinha(CozinhaDto dto){
        this.cdCozinha = dto.cdCozinha();
        this.cdProduto = dto.cdProduto();
        this.cdPedido = dto.cdPedido();
        this.horaSolicitacao = dto.horaSolicitacao();
        this.horaPrepacacao = dto.horaPrepacacao();
        this.tempoPreparacao = dto.tempoPreparacao();
        this.qtd = dto.qtd();
        this.cdFuncionario=dto.cdFuncionario();
        this.nmFuncionario= dto.nmFuncionario();
        this.nrMesa = dto.nrMesa();;
        this.status = dto.status();
        this.observacao = dto.observacao();
        this.nmPrato = dto.nmPrato();
    }

    public Long getCdCozinha() {
        return cdCozinha;
    }

    public void setCdCozinha(Long cdCozinha) {
        this.cdCozinha = cdCozinha;
    }

    public Long getCdProduto() {
        return cdProduto;
    }

    public void setCdProduto(Long cdProduto) {
        this.cdProduto = cdProduto;
    }

    public Long getCdPedido() {
        return cdPedido;
    }

    public void setCdPedido(Long cdPedido) {
        this.cdPedido = cdPedido;
    }

    public LocalDateTime getHoraSolicitacao() {
        return horaSolicitacao;
    }

    public void setHoraSolicitacao(LocalDateTime horaSolicitacao) {
        this.horaSolicitacao = horaSolicitacao;
    }

    public LocalDateTime getHoraPrepacacao() {
        return horaPrepacacao;
    }

    public void setHoraPrepacacao(LocalDateTime horaPrepacacao) {
        this.horaPrepacacao = horaPrepacacao;
    }

    public String getTempoPreparacao() {
        return tempoPreparacao;
    }

    public void setTempoPreparacao(String tempoPreparacao) {
        this.tempoPreparacao = tempoPreparacao;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public Long getCdFuncionario() {
        return cdFuncionario;
    }

    public void setCdFuncionario(Long cdFuncionario) {
        this.cdFuncionario = cdFuncionario;
    }

    public String getNmFuncionario() {
        return nmFuncionario;
    }

    public void setNmFuncionario(String nmFuncionario) {
        this.nmFuncionario = nmFuncionario;
    }

    public int getNrMesa() {
        return nrMesa;
    }

    public void setNrMesa(int nrMesa) {
        this.nrMesa = nrMesa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public void setCdEmpresa(Long cdEmpresa) {
        this.cdEmpresa = cdEmpresa;
    }

    public Long getCdEmpresa() {
        return cdEmpresa;
    }

    public void setNmPrato(String nmPrato) {
        this.nmPrato = nmPrato;
    }

    public String getNmPrato() {
        return nmPrato;
    }
}
