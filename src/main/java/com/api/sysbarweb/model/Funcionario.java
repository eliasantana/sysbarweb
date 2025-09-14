package com.api.sysbarweb.model;

import com.api.sysbarweb.dto.FuncionarioDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "funcionario")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdFuncionario;

    private String nome;
    private String caminhoImagem;
    private String telefone;
    private String login;
    private String senha;
    private String snAtivo;
    private String nrRg;
    private String nrCpf;
    private String nrCnh;
    private String observacao;
    private LocalDate dtvalidadeCNH;
    private LocalDate dtDesligamento;
    private LocalDate dtAdmissao;
    private LocalDate dtNascimento;
    private LocalDate dtInclusao;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionario")
    @JsonIgnore
    private List<Caixa> caixas;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cd_empresa")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "cd_cargo")
    private Cargo cargo;
    public Funcionario(){

    }

    public Funcionario(FuncionarioDto dto) {
        this.cdFuncionario = dto.cdFuncionario();
        this.nome = dto.nome();
        this.caminhoImagem = dto.caminhoImagem();
        this.telefone = dto.telefone();
        this.login = dto.login();
        this.senha = dto.senha();
        this.snAtivo = dto.snAtivo();
        this.nrRg = dto.nrRg();
        this.nrCpf = dto.nrCpf();
        this.nrCnh = dto.nrCnh();
        this.observacao = dto.observacao();
        this.dtvalidadeCNH = dto.dtvalidadeCNH();
        this.dtDesligamento = dto.dtDesligamento();
        this.dtAdmissao = dto.dtAdmissao();
        this.dtNascimento = dto.dtNascimento();
        this.dtInclusao = dto.dtInclusao();
//        this.cargo = dto.cargo();
//        this.empresa = dto.empresa();
//        this.nome = dto.nome();
//        this.caixas = dto.caixas();

    }

    public Long getCdFuncionario() {
        return cdFuncionario;
    }

    public void setCdFuncionario(Long cdFuncionario) {
        this.cdFuncionario = cdFuncionario;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSnAtivo() {
        return snAtivo;
    }

    public void setSnAtivo(String snAtivo) {
        this.snAtivo = snAtivo;
    }

    public String getNrRg() {
        return nrRg;
    }

    public void setNrRg(String nrRg) {
        this.nrRg = nrRg;
    }

    public String getNrCpf() {
        return nrCpf;
    }

    public void setNrCpf(String nrCpf) {
        this.nrCpf = nrCpf;
    }

    public String getNrCnh() {
        return nrCnh;
    }

    public void setNrCnh(String nrCnh) {
        this.nrCnh = nrCnh;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public LocalDate getDtvalidadeCNH() {
        return dtvalidadeCNH;
    }

    public void setDtvalidadeCNH(LocalDate dtvalidadeCNH) {
        this.dtvalidadeCNH = dtvalidadeCNH;
    }

    public LocalDate getDtDesligamento() {
        return dtDesligamento;
    }

    public void setDtDesligamento(LocalDate dtDesligamento) {
        this.dtDesligamento = dtDesligamento;
    }

    public LocalDate getDtAdmissao() {
        return dtAdmissao;
    }

    public void setDtAdmissao(LocalDate dtAdmissao) {
        this.dtAdmissao = dtAdmissao;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public LocalDate getDtInclusao() {
        return dtInclusao;
    }

    public void setDtInclusao(LocalDate dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCaixas(List<Caixa> caixas) {
        this.caixas = caixas;
    }

    public List<Caixa> getCaixas() {
        return caixas;
    }
}
