package com.api.sysbarweb.services;

import com.api.sysbarweb.dto.CargoDto;
import com.api.sysbarweb.exception.CargoException;
import com.api.sysbarweb.model.Cargo;
import com.api.sysbarweb.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CargoServices {
    @Autowired
    CargoRepository repository;

    /**
     * Lista todos os cargos
     * */
    public ResponseEntity<List<CargoDto>> listar() {
        List<Cargo> listaDeCargo = repository.listarTodos();
        if (listaDeCargo.isEmpty()){
            throw new CargoException("Nenhum cargo localizado!");
        }
        List<CargoDto> listaDeCargoDto = listaDeCargo.stream()
                .map(CargoDto::new)
                .collect(Collectors.toList());
       return ResponseEntity.ok(listaDeCargoDto);
    }
    /**
     * Adiciona um Cargo se ele ainda não existir
     * Author: Elias Santana
     * Data: 07-09-2024
     * */
    public ResponseEntity<CargoDto> adicionar(CargoDto dto, UriComponentsBuilder builder) {
        Optional<Cargo> cargoLocalizado =repository.existeCargo(dto.dsCargo());
        if (!cargoLocalizado.isEmpty()){
            throw new CargoException("O cargo informado já existe!");        }
        Cargo cargoSalvo = repository.save(new Cargo(dto));
        URI uri =builder.path("/cargo/listar/").buildAndExpand(new CargoDto(cargoSalvo).cdCargo()).toUri();
        return ResponseEntity.created(uri).build();

    }


    public ResponseEntity<CargoDto> alterar(CargoDto dto) {
        Optional<Cargo> cargoAntigo = repository.localizar(dto.cdCargo());
        if (cargoAntigo.isEmpty()){
            throw new CargoException("O Cargo informado não foi localizado!");
        }
        Cargo cargoAlterado =repository.save(new Cargo(dto));
        return ResponseEntity.ok(new CargoDto(cargoAlterado));
    }

    public ResponseEntity<CargoDto> excluir(Long id) {
        Optional<Cargo> cargoLocalizado = repository.findById(id);
        if (cargoLocalizado.isPresent()){
            cargoLocalizado.get().setSnAtivo("N");
            repository.save(cargoLocalizado.get());
        }else{
            throw new CargoException(String.format("O cargo de ID %s não foi Localizado ou não existe!",id));
        }
        return ResponseEntity.noContent().build();
    }
}
