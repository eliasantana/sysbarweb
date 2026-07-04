package com.api.sysbarweb.dto;

public class LoginValidate {
    private Long cdEmpresa;
    private String nmEmpresa;
    private String endereco;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String cnpj;
    private Long cdFuncionario;
    private String nmFuncionario;
    private Long cdCargo;
    private String dsCargo;

    public LoginValidate(Long cdEmpresa,
                         String nmEmpresa,
                         Long cdFuncionario,
                         String nmFuncionario,
                         Long cdCargo,
                         String dsCargo,
                         String endereco,
                         String bairro, String cep, String cidade, String uf, String cnpj) {
        this.cdEmpresa = cdEmpresa;
        this.nmEmpresa = nmEmpresa;
        this.cdFuncionario = cdFuncionario;
        this.nmFuncionario = nmFuncionario;
        this.cdCargo = cdCargo;
        this.dsCargo = dsCargo;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.uf = uf;
        this.cnpj = cnpj;

    }
    LoginValidate(){

    }

    public Long getCdEmpresa() {
        return cdEmpresa;
    }

    public void setCdEmpresa(Long cdEmpresa) {
        this.cdEmpresa = cdEmpresa;
    }

    public String getNmEmpresa() {
        return nmEmpresa;
    }

    public void setNmEmpresa(String nmEmpresa) {
        this.nmEmpresa = nmEmpresa;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    //Endereco, bairro - cep - cidade - uf - cnpj

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCep() {
        return cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getUf() {
        return uf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
