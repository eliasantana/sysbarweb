package com.api.sysbarweb.services;

import com.api.sysbarweb.dto.CozinhaDto;
import com.api.sysbarweb.exception.CozinhaException;
import com.api.sysbarweb.model.Cozinha;
import com.api.sysbarweb.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CozinhaServices {
    @Autowired
    CozinhaRepository repository;
    @Autowired
    UtilsServices utilsServices;

    public ResponseEntity<List<CozinhaDto>> statusCozinha(Long idemplogada, Long nrmesa) {
        List<Cozinha> listaCozinha = repository.statusCozinha(idemplogada, nrmesa);
        List<CozinhaDto> listaDto = listaCozinha.stream().map(c-> new CozinhaDto(c)).collect(Collectors.toList());
       return ResponseEntity.ok(listaDto);
    }

    public Optional<Cozinha> localizaPrato(Long idcozinha) {
        return repository.localizarPrato(idcozinha);
    }

    public CozinhaDto iniciarPreparo(Optional<Cozinha> prato) {
        Cozinha pratoSalvo = new Cozinha();
        if (null!=prato.get().getHoraPrepacacao()){
            pratoSalvo=repository.save(prato.get());
        }else{
            throw new ClassCastException("O prato informado já foi Iniciado");
        }
        return new CozinhaDto(pratoSalvo);
    }


    public String calculaTempoPreparacao(LocalDateTime inicio, LocalDateTime fim) {

        Duration duration = Duration.between(inicio, fim);
        long horas = duration.toHours();
        long minutos = duration.toMinutes();
        long segundos = (duration.toSeconds()/1000);
        String segundosFormat = String.valueOf(segundos);
        if (segundos < 10) {
            segundosFormat=String.valueOf("0").concat(String.valueOf(segundos));
        }
        return horas+":"+minutos+":"+segundosFormat;

    }

    public ResponseEntity<CozinhaDto> liberarPrato(Optional<Cozinha> prato) {
        Cozinha c = repository.save(prato.get());
        return ResponseEntity.ok(new CozinhaDto(c));
    }


    public ResponseEntity<CozinhaDto> removerPrato(Long idemplogada, Long idfuncionario, Long idcozinha) {
        utilsServices.validaEmpresaLogada(idemplogada);
        utilsServices.validaFuncionario(idemplogada, idfuncionario);
        Optional<Cozinha> prato =repository.localizarPrato(idcozinha);
        Cozinha pratoSalvo = new Cozinha();
        if(prato.isEmpty()){
            throw  new CozinhaException("O prato informado não foilocalizado!");
        }else {
            if (!prato.get().getStatus().equalsIgnoreCase("L")){
                if (prato.get().getQtd() > 1){
                    prato.get().setQtd(prato.get().getQtd()-1);
                    pratoSalvo = repository.save(prato.get());
                    return ResponseEntity.ok(new CozinhaDto(pratoSalvo));
                }else{
                    repository.delete(prato.get());
                    return ResponseEntity.ok(new CozinhaDto(prato.get()));
                }
            }else{
                throw  new CozinhaException("O prato informado já foi liberado e não pode ser removido!");
            }
        }
    }
}
