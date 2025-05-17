package com.api.sysbarweb.services;

import com.api.sysbarweb.dto.FuncionarioDto;
import com.api.sysbarweb.dto.LoginValidate;
import com.api.sysbarweb.exception.CargoException;
import com.api.sysbarweb.exception.EmpresaException;
import com.api.sysbarweb.exception.FuncionarioException;
import com.api.sysbarweb.model.Cargo;
import com.api.sysbarweb.model.Empresa;
import com.api.sysbarweb.model.Funcionario;
import com.api.sysbarweb.repository.CargoRepository;
import com.api.sysbarweb.repository.EmpresaRepository;
import com.api.sysbarweb.repository.FuncionarioResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioServices {
    @Autowired
    FuncionarioResponsitory repository;
    @Autowired
    CargoRepository cargoRepository;

    @Autowired
    EmpresaRepository empresaRepository;

    public ResponseEntity<List<FuncionarioDto>> listar(@PathVariable Long cdempresa) {
        List<Funcionario> funcionarioList = repository.listar(cdempresa);
        if (funcionarioList.isEmpty()) {
            throw new FuncionarioException("Nenhum Funcionário Localizado!");
        }
        List<FuncionarioDto> funcionarioDto = funcionarioList.stream().map(f -> new FuncionarioDto(f)).collect(Collectors.toList());
        return ResponseEntity.ok(funcionarioDto);
    }
    public ResponseEntity<LoginValidate> validaAutenticacao(String cpf, String password) {
        Optional<Funcionario> funcionarioLocalizado=repository.localizarFuncionario(cpf);
        if (funcionarioLocalizado.isEmpty()) {
            throw new FuncionarioException("Funcionário não localizado, acesso não autorizado!");
        }else if (funcionarioLocalizado.get().getSenha().equals(password)){
             LoginValidate loginValidate = new LoginValidate(funcionarioLocalizado.get().getEmpresa().getCdEmpresa(),
                    funcionarioLocalizado.get().getEmpresa().getNomeEmpresa(),
                    funcionarioLocalizado.get().getCdFuncionario(),
                    funcionarioLocalizado.get().getNome(),
                    funcionarioLocalizado.get().getCargo().getCdCargo(),
                    funcionarioLocalizado.get().getCargo().getDsCargo());
            return ResponseEntity.ok(loginValidate);
        }else{
            throw new FuncionarioException("Usuário ou senha Inválido!");
        }
    }
    /**
     * Transfere um funcionário entre empresas
     * */
    @Query(value = "select * from funcionario where cd_empresa=:idemplogada and cd_funcionario=:idfuncionario")
    public ResponseEntity<Funcionario> transferir(Long idemplogada, Long idfuncionario, Long idempdestino) {
        List<Funcionario> listaDeFuncioinario = repository.localizarFuncionario(idemplogada, idfuncionario);
        if (listaDeFuncioinario.isEmpty()) {
            throw new FuncionarioException("Funcionário não localizado");
        }
        Optional<Empresa> empresaDestino = empresaRepository.getEmpresa(idempdestino);
        if (empresaDestino.isEmpty()){
            throw new EmpresaException("A empresa destino não localizda!");
        }
        Funcionario f = listaDeFuncioinario.get(0);
        f.setEmpresa(empresaDestino.get());
        repository.save(f);
        return ResponseEntity.ok().build();

    }

    public ResponseEntity<FuncionarioDto> adicionarFuncionario(FuncionarioDto dto, Long idemplogada, Long idcargo, UriComponentsBuilder builder) {
        Optional<Funcionario> funcionario = repository.localizarFuncionario(dto.nrCpf(), dto.nrRg());
        Optional<Cargo> cargo = cargoRepository.localizar(idcargo);
        if (cargo.isEmpty()){
            throw new CargoException("O Cargo informado não existe!");
        }
        Funcionario funcionarioSalvo = new Funcionario();
        if (funcionario.isEmpty()) {
            Funcionario f = new Funcionario(dto);
            Empresa e = new Empresa();
            e.setCdEmpresa(idemplogada);
            f.setEmpresa(e);
            f.setCargo(cargo.get());
            funcionarioSalvo = repository.save(f);
        } else {
            throw new FuncionarioException("O Funcionário " + funcionario.get().getNome() + " já existe na empresa logada " + idemplogada);
        }
        var uri = builder.path("/funcionario/listar/").buildAndExpand(idemplogada.toString()).toUri();
        return ResponseEntity.created(uri).build();
    }

    public ResponseEntity<FuncionarioDto> excluirFuncinario(Long idemplogada, Long idfuncionario) {
      List<Funcionario> funcionario = repository.localizarFuncionario(idemplogada, idfuncionario);
      if (funcionario.isEmpty()){
          throw new FuncionarioException("O funcionário informado não foi localizado na empresa logada!");
      }
      Funcionario f = funcionario.get(0);
      f.setSnAtivo("N");
      Funcionario funcsalvo =repository.save(f);
      return  ResponseEntity.ok( new FuncionarioDto(funcsalvo));
    }

    public ResponseEntity<FuncionarioDto> alterar(FuncionarioDto dto, Long idemplogada, UriComponentsBuilder builder) {
       List<Funcionario> funcLocalizado = repository.localizarFuncionario(idemplogada, dto.cdFuncionario());
       if (funcLocalizado.isEmpty()){
           throw new FuncionarioException("Funcionário não localizado!");
       }
       Funcionario funcionarioSalvo =repository.save(new Funcionario(dto));
       var uri = builder.path("/funcionario/listar/").buildAndExpand(funcionarioSalvo.getCdFuncionario().toString()).toUri();
       return ResponseEntity.created(uri).build();
    }

    public ResponseEntity<FuncionarioDto> promoverFuncionario(Long idemplogada, Long idfuncionario, Long idnovocargo) {
       List<Funcionario> funcionarios = repository.localizarFuncionario(idemplogada,idfuncionario);
        if (funcionarios.isEmpty()){
            throw new FuncionarioException("Nenhum funcionário localizado!");
        }
        Optional <Cargo> cargoLocalizado = cargoRepository.localizar(idnovocargo);
        if (cargoLocalizado.isEmpty()){
           throw  new CargoException("O Cargo informado não existe!");
        }
        Cargo c = new Cargo();
        c.setCdCargo(idnovocargo);
        Funcionario f = funcionarios.get(0);
        f.setCargo(c);
        Funcionario funcionarioSalvo= repository.save(f);
        return ResponseEntity.ok(new FuncionarioDto(funcionarioSalvo));
    }


}
