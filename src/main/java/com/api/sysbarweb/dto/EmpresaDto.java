package com.api.sysbarweb.dto;

import com.api.sysbarweb.model.Empresa;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class EmpresaDto {
    private Long cdEmpresa;
    @NotNull(message = "O nome da empresa não pode ser vazio!")
    private String nomeEmpresa;
    @NotNull(message = "O endereço é obrigatório")
    private String  endereco;
    @NotNull(message = "O campo número é obrigatório!")
    private int numero;
    @NotNull(message = "O campo Bairro é obrigatório!")
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String telefone;
    @NotNull(message = "O celular da empresa não pode ser vazio!")
    private String celular;
    @NotNull(message = "O e-mail da empresa não pode ser vazio!")
    private String email;
    private String logo;
    @NotNull(message = "O CNPJ é um campo obrigatório não pode ser vazio!")
    private String cnpj;
    private String localBackup;
    private String chaveLicenca;
    private LocalDate dtCadastro;
    private String complemento;
    @NotNull(message = "O campo snBackup da empresa não pode ser vazio!")
    private String snBackupAuto;
    @NotNull(message = "O campo snAtivaDelivery não pode ser vazio")
    private String snAtivaDelivery;
    @NotNull(message = "O campo snAtivaCozinha não pode ser vazio")
    private String snAtivaCozinha;
    @NotNull(message = "O campo snAtivo não pode ser vazio")
    private String snAtivo;
    @NotNull(message = "Teto de Desconto é obrigatório")
    private Double tetoDesconto;

    public EmpresaDto(Empresa empresa){
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

    public EmpresaDto(){

    }
    public Long getCdEmpresa() {
        return cdEmpresa;
    }

    public void setCdEmpresa(Long cdEmpresa) {
        this.cdEmpresa = cdEmpresa;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getLocalBackup() {
        return localBackup;
    }

    public void setLocalBackup(String localBackup) {
        this.localBackup = localBackup;
    }

    public String getChaveLicenca() {
        return chaveLicenca;
    }

    public void setChaveLicenca(String chaveLicenca) {
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

    public String getSnBackupAuto() {
        return snBackupAuto;
    }

    public void setSnBackupAuto(String snBackupAuto) {
        this.snBackupAuto = snBackupAuto;
    }

    public String getSnAtivaDelivery() {
        return snAtivaDelivery;
    }

    public void setSnAtivaDelivery(String snAtivaDelivery) {
        this.snAtivaDelivery = snAtivaDelivery;
    }

    public String getSnAtivaCozinha() {
        return snAtivaCozinha;
    }

    public void setSnAtivo(String snAtivo) {
        this.snAtivo = snAtivo;
    }

    public String getSnAtivo() {
        return snAtivo;
    }

    public void setSnAtivaCozinha(String snAtivaCozinha) {
        this.snAtivaCozinha = snAtivaCozinha;
    }

    public void setTetoDesconto(Double tetoDesconto) {
        this.tetoDesconto = tetoDesconto;
    }

    public Double getTetoDesconto() {
        return tetoDesconto;
    }

    @Override
    public String toString() {
        return "EmpresaDto{" +
                "cdEmpresa=" + cdEmpresa +
                ", nomeEmpresa='" + nomeEmpresa + '\'' +
                ", endereco='" + endereco + '\'' +
                ", numero=" + numero +
                ", bairro='" + bairro + '\'' +
                ", cep='" + cep + '\'' +
                ", cidade='" + cidade + '\'' +
                ", uf='" + uf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", celular='" + celular + '\'' +
                ", email='" + email + '\'' +
                ", logo='" + logo + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", localBackup='" + localBackup + '\'' +
                ", chaveLicenca='" + chaveLicenca + '\'' +
                ", dtCadastro='" + dtCadastro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", snBackupAuto='" + snBackupAuto + '\'' +
                ", snAtivaDelivery='" + snAtivaDelivery + '\'' +
                ", snAtivaCozinha='" + snAtivaCozinha + '\'' +
                '}';
    }
}
