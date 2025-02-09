package com.api.sysbarweb.services;

import com.api.sysbarweb.dto.CaixaDto;
import com.api.sysbarweb.exception.CaixaException;
import com.api.sysbarweb.exception.FuncionarioException;
import com.api.sysbarweb.model.Caixa;
import com.api.sysbarweb.model.Empresa;
import com.api.sysbarweb.model.Funcionario;
import com.api.sysbarweb.repository.CaixaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CaixaServices {
    @Autowired
    CaixaRepository repository;
    @Autowired
    UtilsServices utilsServices;

    public ResponseEntity<CaixaDto> abrirCaixa(Long idemplogada, Long idfuncionario, String password, BigDecimal saldoinicial) {
        Optional<Empresa> empresa = utilsServices.validaEmpresaLogada(idemplogada);
        List<Funcionario> funcionario = utilsServices.validaFuncionario(idemplogada,idfuncionario);
        if (!funcionario.get(0).getSenha().equals(password)){
            throw new CaixaException("Senha Inválida!");
        }else if (!funcionario.get(0).getCargo().getDsCargo().equals("Caixa")){
            throw new CaixaException("O funcionário não possui permissão para realizar a abertura do caixa!");
        }else{
            Caixa c = new Caixa();
            c.setEmpresa(empresa.get());
            c.setFuncionario(funcionario.get(0));
            c.setSaldoInicial(saldoinicial);
            c.setSaldoFinal(BigDecimal.ZERO);
            c.setStatus("A");
            c.setDtAbertura(LocalDate.now());
            Caixa caixaSalvo= repository.save(c);
            return ResponseEntity.ok().build();
        }
    }

    public Caixa localizaCaixa(Long idemplogada, Long idfuncionario) {
       Caixa c = repository.localizaCaixa(idemplogada, idfuncionario);
       if (c.getCdCaixa()!=null){
           return c;
       }else{
           throw new CaixaException("Caixa não localizado!");
       }

    }

    public ResponseEntity<CaixaDto> fecharCaixa(Long idemplogada, Long idfuncionario, String password) {
        Caixa c = repository.localizaCaixa(idemplogada, idfuncionario);
        if (c!=null){
            if (c.getStatus().equalsIgnoreCase("f")){
                throw new CaixaException("O Caixa informado já encontra-se fechado!");
            }
            List <Funcionario> funcionario = utilsServices.validaFuncionario(idemplogada, idfuncionario);
            if (password.equals(funcionario.get(0).getSenha()) && funcionario.get(0).getCargo().getDsCargo().equalsIgnoreCase("caixa")){
                c.setStatus("F");
                Caixa caixaSalvo = repository.save(c);
                return ResponseEntity.ok().build();
            }else{
                throw  new CaixaException("Usuário ou senha inválidos!");
            }
        }else{
            throw new CaixaException("Caixa não localizado!");
        }
    }

    public ResponseEntity<CaixaDto> reabrirCaixa(Long idemplogada, Long idfuncionario, String password) {
        utilsServices.validaEmpresaLogada(idemplogada);
        List<Funcionario> funcionarios = utilsServices.validaFuncionario(idemplogada, idfuncionario);
        if (password.equals(funcionarios.get(0).getSenha()) && funcionarios.get(0).getCargo().getDsCargo().equalsIgnoreCase("caixa")){
            Caixa c = repository.localizaCaixa(idemplogada,idfuncionario);
            c.setStatus("A");
            repository.save(c);
            return ResponseEntity.ok().build();
        }else{
            throw new CaixaException("Usuário ou senha inválidos");
        }
    }
}
