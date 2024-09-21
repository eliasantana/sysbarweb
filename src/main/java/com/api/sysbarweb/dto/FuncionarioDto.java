package com.api.sysbarweb.dto;

import com.api.sysbarweb.model.Cargo;
import com.api.sysbarweb.model.Empresa;
import com.api.sysbarweb.model.Funcionario;

import java.time.LocalDate;

public record FuncionarioDto(
        Long cdFuncionario,
        String caminhoImagem,
        String telefone,
        String login,
        String senha,
        String snAtivo,
        String nrRg,
        String nrCpf,
        String nrCnh,
        String observacao,
        LocalDate dtvalidadeCNH,
        LocalDate dtDesligamento,
        LocalDate dtAdmissao,
        LocalDate dtNascimento,
        LocalDate dtInclusao,
        Cargo cargo,
        Empresa empresa
) {
    public FuncionarioDto(Funcionario f){
        this(f.getCdFuncionario(),
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
                f.getEmpresa());
    }
}
