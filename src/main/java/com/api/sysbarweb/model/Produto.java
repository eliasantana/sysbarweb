package com.api.sysbarweb.model;

import com.api.sysbarweb.dto.ProdutoDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;

@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdProduto;
    private String cdNCM; // Código NCM do produto
    @NotNull(message = "O código interno é obrigatório")
    private String cdInterno; // Código interno do Produto
    @NotNull(message = "A Descrição do Produto é obrigatório!")
    private String dsProduto;
    //@NotNull(message = "O tipo do produto é obrigatório!")
    private String tipo;
    private LocalDate dtInclusao;
    @NotNull(message = "O campo snAtivo não pode ser Nulo!")
    private String snAtivo;

    public Produto(){

    }

    public Produto(ProdutoDto dto){
        this.cdProduto = dto.cdProduto();
        this.cdNCM = dto.cdNCM();
        this.cdInterno = dto.cdInterno();
        this.dsProduto = dto.dsProduto();
        this.tipo = dto.tipo();
        this.dtInclusao=LocalDate.now();
        this.snAtivo = dto.snAtivo();
    }

    public Long getCdProduto() {
        return cdProduto;
    }

    public void setCdProduto(Long cdProduto) {
        this.cdProduto = cdProduto;
    }

    public String getCdNCM() {
        return cdNCM;
    }

    public void setCdNCM(String cdNCM) {
        this.cdNCM = cdNCM;
    }

    public String getCdInterno() {
        return cdInterno;
    }

    public void setCdInterno(String cdInterno) {
        this.cdInterno = cdInterno;
    }

    public void setDsProduto(String dsProduto) {
        this.dsProduto = dsProduto;
    }

    public String getDsProduto() {
        return dsProduto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDtInclusao() {
        return dtInclusao;
    }

    public void setDtInclusao(LocalDate dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    public void setSnAtivo(String snAtivo) {
        this.snAtivo = snAtivo;
    }

    public String getSnAtivo() {
        return snAtivo;
    }
}
