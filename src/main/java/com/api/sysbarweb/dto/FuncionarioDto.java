package com.api.sysbarweb.dto;

import com.api.sysbarweb.model.Caixa;
import com.api.sysbarweb.model.Cargo;
import com.api.sysbarweb.model.Empresa;
import com.api.sysbarweb.model.Funcionario;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record FuncionarioDto(
        Long cdFuncionario,

        String nome,
        String caminhoImagem,
        String telefone,
        String login,
        String senha,
        String snAtivo,
        @NotNull(message = "o campo RG é obrigatório!")
        String nrRg,
        @NotNull(message = "o campo CPF é obrigatório!")
        String nrCpf,
        String nrCnh,
        String observacao,
        LocalDate dtvalidadeCNH,
        LocalDate dtDesligamento,
        LocalDate dtAdmissao,
        LocalDate dtNascimento,
        LocalDate dtInclusao,
        Cargo cargo,
        Empresa empresa,
        List<Caixa> caixas
) {
    public FuncionarioDto(Funcionario f){
        this(f.getCdFuncionario(),
                f.getNome(),
                f.getCaminhoImagem(),
                f.getTelefone(),
                f.getLogin(),
                f.getSenha(),
                f.getSnAtivo(),
                f.getNrRg(),
                f.getNrCpf(),
                f.getNrCnh(),
                f.getObservacao(),
                f.getDtvalidadeCNH(),
                f.getDtDesligamento(),
                f.getDtAdmissao(),
                f.getDtNascimento(),
                f.getDtInclusao(),
                f.getCargo(),
                f.getEmpresa(), f.getCaixas());
    }
}
