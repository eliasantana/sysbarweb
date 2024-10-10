package com.api.sysbarweb.services;

import com.api.sysbarweb.dto.MesaDto;
import com.api.sysbarweb.exception.EmpresaException;
import com.api.sysbarweb.exception.FuncionarioException;
import com.api.sysbarweb.exception.MesaException;
import com.api.sysbarweb.model.Empresa;
import com.api.sysbarweb.model.Funcionario;
import com.api.sysbarweb.model.Mesa;
import com.api.sysbarweb.repository.EmpresaRepository;
import com.api.sysbarweb.repository.FuncionarioResponsitory;
import com.api.sysbarweb.repository.MesaRepository;
import org.hibernate.dialect.function.ListaggFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MesaServices {
    @Autowired
    MesaRepository repository;

    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    UtilsServices utilsServices;
    @Autowired
    FuncionarioResponsitory funcionarioResponsitory;

    public ResponseEntity<List<MesaDto>> listarTodas(Long idempresa) {
       Optional<Empresa> emp= empresaRepository.getEmpresa(idempresa);
       if(emp.isEmpty()){
           throw new EmpresaException("A empresa informada não localizda!");
       }
       List<Mesa> mesas = repository.listarTodas(idempresa);
       if (mesas.isEmpty()){
           throw new MesaException("Não existe mesas cadastradas!");
       }
       List<MesaDto> mesasDto= mesas.stream().map(MesaDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(mesasDto);
    }

    public ResponseEntity<MesaDto> adicionar(Long idemplogada, int nrmesa, Long idfuncionario, UriComponentsBuilder builder) {
        Optional<Empresa> emp= empresaRepository.getEmpresa(idemplogada);
        if(repository.existeMesa(idemplogada, nrmesa).isPresent()){
            throw new MesaException("A mesa informada já existe");
        }
        if(emp.isEmpty()){
            throw new EmpresaException("A empresa informada não localizda!");
        }
        List<Funcionario> funcionario=funcionarioResponsitory.localizarFuncionario(idemplogada, idfuncionario) ;
        if (funcionario.isEmpty()){
             throw new FuncionarioException("O funcionário informado não foi localizado na empresa logada!");
        }
        Funcionario f = funcionario.get(0);
        if (!f.getCargo().getDsCargo().equals("Garçom")){
             throw new FuncionarioException("O funcionário informado não é um Garçom!");
        }
        Empresa e = new Empresa();
        e.setCdEmpresa(idemplogada);
        Mesa m = new Mesa();
        m.setNrMesa(nrmesa);
        m.setEmpresa(e);
        m.setFuncionario(f);
        Mesa mesaSalva =repository.save(m);
        URI uri=builder.path("/mesa/listar/{idmesa}").buildAndExpand(new MesaDto(mesaSalva).cdMesa()).toUri();
        return ResponseEntity.created(uri).build();
    }

    public ResponseEntity<List<MesaDto>> listarMesasGarcom(Long idemplogada, Long idfuncionario) {
        Optional<Empresa> empresaLocalizada=utilsServices.validaEmpresaLogada(idemplogada);
        List<Funcionario> funcinario=utilsServices.validaFuncionario(idemplogada, idfuncionario);
        List<Mesa> mesas=repository.listarMesaGarcom(idemplogada, idfuncionario);
        List<MesaDto> mesasDto=mesas.stream().map(MesaDto::new).toList();
        return ResponseEntity.ok(mesasDto);
    }


}
