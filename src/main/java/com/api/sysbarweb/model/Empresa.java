package com.api.sysbarweb.model;

import com.api.sysbarweb.dto.EmpresaDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdEmpresa;
    @NotNull(message = "Campo obrigatório")
    private String nomeEmpresa;
    @NotNull(message = "Campo obrigatório")
    private String endereco;
    private int numero;
    @NotNull(message = "Campo obrigatório")
    private String bairro;
    @NotNull(message = "Campo obrigatório")
    private String cep;
    @NotNull(message = "Campo obrigatório")
    private String cidade;
    @NotNull(message = "Campo obrigatório")
    private String uf;
    private String telefone;
    @NotNull(message = "Campo obrigatório")
    private String celular;
    private String email;
    private String logo;
    @NotNull(message = "Campo obrigatório")
    private String cnpj;
    private String localBackup;
    @NotNull(message = "Campo obrigatório")
    private String chaveLicenca;
    private LocalDate dtCadastro;
    private String complemento;
    @NotNull(message = "O campo snBackupAuto é obrigatório!")
    private String snBackupAuto;
    @NotNull (message = "O campo snAtivaDelivery é obrigatório!")
    private String snAtivaDelivery;
    @NotNull(message = "O campo snAtivaCozinha é obrigatório!")
    private String snAtivaCozinha;
    @NotNull(message = "O campo snAtivo  é obrigatório!")
    private String snAtivo;

    private double tetoDesconto;
    @OneToMany(mappedBy = "empresa")
    @JsonIgnore
    List<Caixa> caixas;

    public Empresa(){

    }
    public Empresa(EmpresaDto empresa){
        this.cdEmpresa = empresa.getCdEmpresa();
        this.nomeEmpresa = empresa.getNomeEmpresa();
        this.endereco = empresa.getEndereco();
        this.numero = empresa.getNumero();
        this.bairro = empresa.getBairro();
        this.cep = empresa.getCep();
        this.cidade = empresa.getCidade();
        this.uf = empresa.getUf();
        this.telefone = empresa.getTelefone();
        this.celular = empresa.getCelular();
        this.email = empresa.getEmail();
        this.logo = empresa.getLogo();
        this.cnpj = empresa.getCnpj();
        this.localBackup = empresa.getLocalBackup();
        this.chaveLicenca = empresa.getChaveLicenca();
        this.dtCadastro = empresa.getDtCadastro();
        this.complemento = empresa.getComplemento();
        this.snBackupAuto = empresa.getSnBackupAuto();
        this.snAtivaCozinha = empresa.getSnAtivaCozinha();
        this.snAtivaDelivery = empresa.getSnAtivaDelivery();
        this.snAtivo = empresa.getSnAtivo();
        this.tetoDesconto = empresa.getTetoDesconto();
    }

    public Long getCdEmpresa() {
        return cdEmpresa;
    }

    public void setCdEmpresa(Long cdEmpresa) {
        this.cdEmpresa = cdEmpresa;
    }

    public @NotNull(message = "Campo obrigatório") String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(@NotNull(message = "Campo obrigatório") String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public @NotNull(message = "Campo obrigatório") String getEndereco() {
        return endereco;
    }

    public void setEndereco(@NotNull(message = "Campo obrigatório") String endereco) {
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public @NotNull(message = "Campo obrigatório") String getBairro() {
        return bairro;
    }

    public void setBairro(@NotNull(message = "Campo obrigatório") String bairro) {
        this.bairro = bairro;
    }

    public @NotNull(message = "Campo obrigatório") String getCep() {
        return cep;
    }

    public void setCep(@NotNull(message = "Campo obrigatório") String cep) {
        this.cep = cep;
    }

    public @NotNull(message = "Campo obrigatório") String getCidade() {
        return cidade;
    }

    public void setCidade(@NotNull(message = "Campo obrigatório") String cidade) {
        this.cidade = cidade;
    }

    public @NotNull(message = "Campo obrigatório") String getUf() {
        return uf;
    }

    public void setUf(@NotNull(message = "Campo obrigatório") String uf) {
        this.uf = uf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public @NotNull(message = "Campo obrigatório") String getCelular() {
        return celular;
    }

    public void setCelular(@NotNull(message = "Campo obrigatório") String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public @NotNull(message = "Campo obrigatório") String getCnpj() {
        return cnpj;
    }

    public void setCnpj(@NotNull(message = "Campo obrigatório") String cnpj) {
        this.cnpj = cnpj;
    }

    public String getLocalBackup() {
        return localBackup;
    }

    public void setLocalBackup(String localBackup) {
        this.localBackup = localBackup;
    }

    public @NotNull(message = "Campo obrigatório") String getChaveLicenca() {
        return chaveLicenca;
    }

    public void setChaveLicenca(@NotNull(message = "Campo obrigatório") String chaveLicenca) {
        this.chaveLicenca = chaveLicenca;
    }

    public LocalDate getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(LocalDate dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @NotNull(message = "O campo snBackupAuto é obrigatório!")
    public String getSnBackupAuto() {
        return snBackupAuto;
    }

    public void setSnBackupAuto(@NotNull(message = "O campo snBackupAuto é obrigatório!") String snBackupAuto) {
        this.snBackupAuto = snBackupAuto;
    }

    @NotNull(message = "O campo snAtivaDelivery é obrigatório!")
    public String getSnAtivaDelivery() {
        return snAtivaDelivery;
    }

    public void setSnAtivaDelivery(@NotNull(message = "O campo snAtivaDelivery é obrigatório!") String snAtivaDelivery) {
        this.snAtivaDelivery = snAtivaDelivery;
    }

    @NotNull(message = "O campo snAtivaCozinha é obrigatório!")
    public String getSnAtivaCozinha() {
        return snAtivaCozinha;
    }

    public void setSnAtivaCozinha(@NotNull(message = "O campo snAtivaCozinha é obrigatório!") String snAtivaCozinha) {
        this.snAtivaCozinha = snAtivaCozinha;
    }

    public void setSnAtivo(String snAtivo) {
        this.snAtivo = snAtivo;
    }

    public String getSnAtivo() {
        return snAtivo;
    }

    public void setTetoDesconto(double tetoDesconto) {
        this.tetoDesconto = tetoDesconto;
    }

    public double getTetoDesconto() {
        return tetoDesconto;
    }

    public void setCaixas(List<Caixa> caixas) {
        this.caixas = caixas;
    }

    public List<Caixa> getCaixas() {
        return caixas;
    }
}
