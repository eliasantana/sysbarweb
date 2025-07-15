package com.api.sysbarweb.services;

import com.api.sysbarweb.projections.TxEstatisticaProjection;
import com.api.sysbarweb.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EstatisticaServices {

    @Autowired
    EmpresaRepository empresaRepository;
    public ResponseEntity<TxEstatisticaProjection> getStatistica(Long cdEmpresa) {
        TxEstatisticaProjection projection = empresaRepository.getStatistica(cdEmpresa);
        return ResponseEntity.ok(projection);
    }
}
