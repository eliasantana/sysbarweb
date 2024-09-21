package com.api.sysbarweb.services;

import com.api.sysbarweb.dto.FuncionarioDto;
import com.api.sysbarweb.exception.FuncionarioException;
import com.api.sysbarweb.model.Funcionario;
import com.api.sysbarweb.repository.FuncionarioResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioServices
{
    @Autowired
    FuncionarioResponsitory repository;

    public ResponseEntity<List<FuncionarioDto>> listar(@PathVariable Long cdempresa) {
        List<Funcionario> funcionarioList = repository.listar(cdempresa);
        if (funcionarioList.isEmpty()){
            throw new FuncionarioException("Nenhuma empresa Localizada!");
        }
        List<FuncionarioDto> funcionarioDto =  funcionarioList.stream().map(f -> new FuncionarioDto(f)).collect(Collectors.toList());
        return ResponseEntity.ok(funcionarioDto);
    }
}
