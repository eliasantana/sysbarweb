package com.api.sysbarweb.services;

import com.api.sysbarweb.dto.ItPedidoDto;
import com.api.sysbarweb.dto.MesaDto;
import com.api.sysbarweb.dto.PedidoDto;
import com.api.sysbarweb.exception.PedidoException;
import com.api.sysbarweb.model.*;
import com.api.sysbarweb.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServices {
    @Autowired
    PedidoRepository repository;

    @Autowired
    UtilsServices utilsServices;

    @Autowired
    MesaServices mesaServices;

    @Autowired
    ItPedidoServices itPedidoServices;
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
        p.setStatusPedido("A");
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

    public ResponseEntity<List<ItPedidoDto>> localizar(Long idemplogada, Long idpedido) {
        return  itPedidoServices.localizar(idemplogada, idpedido);
    }



    public ResponseEntity<PedidoDto> fecharPedido(Long idemplogada, Long idpedido) {
        Optional<Pedido> pedido = utilsServices.validapedido(idemplogada, idpedido);
        Optional<Mesa> mesa= mesaServices.repository.existeMesa(pedido.get().getCdMesa());
        BigDecimal totalPedido  = totalizapedido(idemplogada, pedido.get().getCdPedido());
        pedido.get().setStatusPedido("F");
        pedido.get().setTotalPedido(totalPedido);
        PedidoDto dto = new PedidoDto(repository.save(pedido.get()));
        mesa.get().setStatus("L");
        mesaServices.mesaRepository.save(mesa.get());
        //TODO Gerar movimenação de caixa
        return ResponseEntity.ok().build();
    }

    private BigDecimal totalizapedido(Long idemplogada, Long idpedido) {
       List<ItPedido> itens = itPedidoServices.repository.localizar(idemplogada, idpedido);
       BigDecimal totalItem = BigDecimal.ZERO;
       BigDecimal totalPedido = BigDecimal.ZERO;
       totalPedido = itens.stream()
                .map(item -> item.getVlUnit().multiply(BigDecimal.valueOf(item.getQtd())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
       return totalPedido;
    }
}
