package com.api.sysbarweb.services;

import com.api.sysbarweb.dto.CozinhaDto;
import com.api.sysbarweb.model.Cozinha;
import com.api.sysbarweb.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CozinhaServices {
    @Autowired
    CozinhaRepository repository;

    public ResponseEntity<List<CozinhaDto>> statusCozinha(Long idemplogada, Long idfuncionario) {
        List<Cozinha> listaCozinha = repository.statusCozinha(idemplogada, idfuncionario);
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
}
