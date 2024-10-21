package com.api.sysbarweb.services;

import com.api.sysbarweb.dto.FuncionarioDto;
import com.api.sysbarweb.dto.PedidoDto;
import com.api.sysbarweb.exception.PedidoException;
import com.api.sysbarweb.model.Empresa;
import com.api.sysbarweb.model.Funcionario;
import com.api.sysbarweb.model.Mesa;
import com.api.sysbarweb.model.Pedido;
import com.api.sysbarweb.repository.PedidoRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServices {
    @Autowired
    PedidoRepository repository;

    @Autowired
    UtilsServices utilsServices;

    @Autowired
    MesaServices mesaServices;
    public ResponseEntity<PedidoDto> adicionar(Long idemplogada,
                                               Long idfuncionario,
                                               Long idemesa,
                                               UriComponentsBuilder builder) {
       Optional<Empresa> emp =utilsServices.validaEmpresaLogada(idemplogada);
       List<Funcionario> func = utilsServices.validaFuncionario(idemplogada, idfuncionario);
       Optional<Mesa> mesa = mesaServices.mesaRepository.findById(idemesa);

        Mesa m = utilsServices.validaMesa(mesa);

        if (m.getStatus().equals("L")){
            m.setStatus("O"); // O = Ocupada
            mesaServices.mesaRepository.save(m);
        }

        Pedido p = new Pedido();
        p.setCdEmpresa(emp.get().getCdEmpresa());
        p.setCdFuncionario(func.get(0).getCdFuncionario());
        p.setCdMesa(m.getCdMesa());
        Pedido pedidosalvo = repository.save(p);
        URI uri = builder.path("/pedido/listar/{}").buildAndExpand(new PedidoDto(pedidosalvo).cdPedido()).toUri();

        return ResponseEntity.created(uri).build();
    }

    public ResponseEntity<List<PedidoDto>> listar(Long idemplogada) {
       List<Pedido> pedidosLocalizadao = repository.listar(idemplogada);
       if (pedidosLocalizadao.isEmpty()){
           throw new PedidoException("O pedido informado não foi localizado!");
       }
       List<PedidoDto> pedidosDto = pedidosLocalizadao.stream().map(PedidoDto::new).toList();
        return ResponseEntity.ok(pedidosDto);
    }
}
