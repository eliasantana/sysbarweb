package com.api.sysbarweb.dto;

public class LoginValidate {
    private Long cdEmpresa;
    private String nmEmpresa;
    private Long cdFuncionario;
    private String nmFuncionario;
    private Long cdCargo;
    private String dsCargo;

    public LoginValidate(Long cdEmpresa, String nmEmpresa, Long cdFuncionario, String nmFuncionario, Long cdCargo, String dsCargo) {
        this.cdEmpresa = cdEmpresa;
        this.nmEmpresa = nmEmpresa;
        this.cdFuncionario = cdFuncionario;
        this.nmFuncionario = nmFuncionario;
        this.cdCargo = cdCargo;
        this.dsCargo = dsCargo;
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
}
