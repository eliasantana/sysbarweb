package com.api.sysbarweb.services;

import com.api.sysbarweb.model.ParametrosGlobais;
import com.api.sysbarweb.repository.ParametrosGlobaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParametrosGlobaisServices {
    @Autowired
    ParametrosGlobaisRepository repository;

    public String getChave(String chave) {
      ParametrosGlobais pg = repository.getChave(chave);
       return pg.getValor();
    }
}
