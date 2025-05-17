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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.ArrayList;
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

    @Autowired
    MesaRepository mesaRepository;

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
        m.setStatus("L");
        Mesa mesaSalva =repository.save(m);
        URI uri=builder.path("/mesa/listar/{idmesa}").buildAndExpand(new MesaDto(mesaSalva).cdMesa()).toUri();
        return ResponseEntity.created(uri).build();
    }

    public ResponseEntity<List<MesaDto>> listarMesasGarcom(Long idemplogada, Long idfuncionario) {
        Optional<Empresa> empresaLocalizada=utilsServices.validaEmpresaLogada(idemplogada);
        List<Funcionario> funcinario=utilsServices.validaFuncionario(idemplogada, idfuncionario);
        List<Mesa> mesas=repository.listarMesaGarcom(idemplogada, idfuncionario);
        List<MesaDto> mesasDto = new ArrayList<>();
        for (int i = 0; i < mesas.size(); i++) {
            mesasDto.add(new MesaDto(mesas.get(i)));
        }
        return ResponseEntity.ok(mesasDto);
    }


    public ResponseEntity<List<MesaDto>> intervaloMesa(Long idemplogada,
                                                       Long nrmesainicial,
                                                       Long nrmesafinal,
                                                       Long idfucionario) {
        Optional<Empresa> empresaLocalizada=utilsServices.validaEmpresaLogada(idemplogada);
        List<Funcionario> funcinario=utilsServices.validaFuncionario(idemplogada, idfucionario);
        List<Mesa> mesas=repository.listarMesaGarcom(idemplogada, idfucionario);
        List<Mesa> intervaloMesa = new ArrayList<>();

        Boolean resp = Boolean.FALSE;
        if (nrmesainicial==0 || nrmesainicial > nrmesafinal){
            throw new MesaException("O número inicial da mesa não pode ser maior que o número final");
        }
        for (int i = nrmesainicial.intValue(); i <= nrmesafinal.intValue(); i++) {
                 if (!utilsServices.existMesa(mesas,i)){
                     Mesa m = new Mesa();
                     m.setNrMesa(i);
                     m.setEmpresa(empresaLocalizada.get());
                     m.setFuncionario(funcinario.get(0));
                     m.setStatus("L");// L=Livre
                     intervaloMesa.add(m);
                 }
        }
        if (!intervaloMesa.isEmpty()){
            repository.saveAll(intervaloMesa);
        }
        List<MesaDto> mesasDtos = intervaloMesa.stream().map(MesaDto::new).toList();
        return ResponseEntity.ok(mesasDtos);

    }

    public ResponseEntity<MesaDto> alterarAlteraGarcom(Long idemplogada,int nrmesa , Long idnovocarcom) {
        Optional<Empresa> emp= utilsServices.validaEmpresaLogada(idemplogada);
        List<Funcionario> funcionario = utilsServices.validaFuncionario(idemplogada, idnovocarcom);
        utilsServices.validaMesa(idemplogada, nrmesa);

        if (!"Garçom".equals(funcionario.get(0).getCargo().getDsCargo())){
            throw new FuncionarioException("O Funcionário informado não é um Garçom!");
        }
        Optional<Mesa> mesa = mesaRepository.existeMesa(idemplogada, nrmesa);
        if (mesa.isEmpty()){
            throw new FuncionarioException("A mesa informada não foi localizada!");
        }
       mesa.get().setFuncionario(funcionario.get(0));
       mesa.get().setEmpresa(emp.get());
       if (mesa.get().getFuncionario().getCdFuncionario() !=idnovocarcom){
           mesaRepository.save(mesa.get());
       }
       return ResponseEntity.ok().build();
    }

    public Optional<Mesa> getMesa(Long cdMesa) {
        return repository.existeMesa(cdMesa);
    }
}
