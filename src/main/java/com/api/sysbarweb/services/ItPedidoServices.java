package com.api.sysbarweb.services;

import com.api.sysbarweb.dto.ItPedidoDto;
import com.api.sysbarweb.exception.ItPedidoException;
import com.api.sysbarweb.model.Empresa;
import com.api.sysbarweb.model.ItPedido;
import com.api.sysbarweb.model.Pedido;
import com.api.sysbarweb.repository.ItPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItPedidoServices {

    @Autowired
    ItPedidoRepository repository;

    @Autowired
    UtilsServices utilsServices;

    public ResponseEntity<List<ItPedidoDto>> localizar(Long idemplogada, Long idpedido) {
        Optional<Empresa> emp = utilsServices.validaEmpresaLogada(idemplogada);
        Optional<Pedido> pedido =  utilsServices.validapedido(idemplogada, idpedido);
        List<ItPedido> itens =repository.localizar(idemplogada, idpedido);
        List<ItPedidoDto> itPedidoDtos=itens.stream().map(ItPedidoDto::new).toList();
        return ResponseEntity.ok(itPedidoDtos);
    }

    public ItPedido adicioar(ItPedido itPedido) {
        return repository.save(itPedido);
    }

    public List<ItPedido> validaItemPedido(Long idemplogada, Long idpedido, Long iditpedido) {
        List<ItPedido> itensLocalizados = repository.localizarItemDoPedido(idemplogada, idpedido, iditpedido);
        if (itensLocalizados.isEmpty()){
            throw new ItPedidoException(String.format("Não foi possível localizar os itens para o pedido %s item do pedido %s na empresa %s.", idpedido,iditpedido, idemplogada));
        }
        return itensLocalizados;
    }
}