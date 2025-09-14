package com.api.sysbarweb.services;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import com.api.sysbarweb.dto.EmpresaDto;
import com.api.sysbarweb.exception.EmpresaException;
import com.api.sysbarweb.exception.EmpresaExistsException;
import com.api.sysbarweb.exception.SysbarWebNotFound;
import com.api.sysbarweb.model.Empresa;
import com.api.sysbarweb.repository.EmpresaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpresaServices {
    @Autowired
    EmpresaRepository repository;

    public ResponseEntity<EmpresaDto> getEmpresa(Long id) {
        Optional<Empresa> empresa = repository.getEmpresa(id);
        if (empresa.isEmpty()) {
            throw new SysbarWebNotFound("Empresa não localizada");
        }
        return ResponseEntity.ok(new EmpresaDto(empresa.get()));
    }

    public ResponseEntity<EmpresaDto> adicionar(EmpresaDto dto, UriComponentsBuilder builder) {
        String cnpj = removeCaracteresEspeciais(dto);
        dto.setCnpj(cnpj);
        validaCnPJ(dto.getCnpj());
        List<Optional<Empresa>> empresaLocalizada = repository.existEmpresa(dto.getCnpj());

        Empresa empresaSalva = repository.save(new Empresa(dto));
        var uri = builder.path("/empresa/{id}").buildAndExpand(empresaSalva.getCdEmpresa()).toUri();
        return ResponseEntity.created(uri).body(new EmpresaDto(empresaSalva));
    }

    public ResponseEntity<EmpresaDto> alterar(@RequestBody @Valid EmpresaDto dto, UriComponentsBuilder builder) {
        if (dto.getCdEmpresa() != null) {
            validaCnPJ(dto.getCnpj());
            Empresa empresa = new Empresa(dto);
            EmpresaDto empresaSalvaDto = new EmpresaDto(repository.save(empresa));
            URI uri = builder.path("/empresa/{id}").buildAndExpand(empresaSalvaDto.getCdEmpresa()).toUri();
            return ResponseEntity.created(uri).body(empresaSalvaDto);
        } else {
            throw new EmpresaException("O Campo código é obrigatório para o processo de alteração!");
        }

    }

    public ResponseEntity<EmpresaDto> deletaEmpresa(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new SysbarWebNotFound("A empresa informada não foi localizada!");
        }

        Optional<Empresa> emp =repository.getEmpresa(id);
        emp.get().setSnAtivo("N");
        repository.save(emp.get());
        return ResponseEntity.noContent().build();
    }

    private static String removeCaracteresEspeciais(EmpresaDto dto) {
        String cnpj = dto.getCnpj().replace("/", "");
        cnpj = cnpj.replace("-", "");
        cnpj = cnpj.replace(".", "");
        cnpj.trim();
        return cnpj;
    }
    /**
     * Valida um CNPJ e verifica se a empresa existe de acordo com a operação informada
     * A - Alteração I - Inclusão
     * @param cnpj
     * */
    private void validaCnPJ(String cnpj) {
        if (cnpj.length() < 14) {
            throw new EmpresaExistsException("Cnpj Inválido!");
        }
    }

    public ResponseEntity<EmpresaDto> ativaEmpresa(Long id) {
      Optional<Empresa> emp = repository.findById(id);
      if (emp.isEmpty()){
          throw new EmpresaException("Empresa não localizada!");
      }
      Empresa e=emp.get();
      e.setSnAtivo("S");
      repository.save(e);
      return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<EmpresaDto>> listarTodas() {
        List<Empresa> empresas = repository.listarTodas();
        List<EmpresaDto> empresasDto = empresas.stream()
                .map( EmpresaDto::new  )
                .collect(Collectors.toList());
        return ResponseEntity.ok(empresasDto);
    }
}
