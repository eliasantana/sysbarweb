package com.api.sysbarweb.services;

import com.api.sysbarweb.dto.ItPedidoDto;
import com.api.sysbarweb.dto.ItemDto;
import com.api.sysbarweb.exception.ItPedidoException;
import com.api.sysbarweb.model.Empresa;
import com.api.sysbarweb.model.ItPedido;
import com.api.sysbarweb.model.Pedido;
import com.api.sysbarweb.repository.ItPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItPedidoServices {

    @Autowired
    ItPedidoRepository repository;

    @Autowired
    UtilsServices utilsServices;

    public ResponseEntity<List<ItemDto>> localizar(Long idemplogada, Long idpedido) {
        Optional<Empresa> emp = utilsServices.validaEmpresaLogada(idemplogada);
        Optional<Pedido> pedido =  utilsServices.validapedido(idemplogada, idpedido);
        List<ItPedido> itens =repository.localizar(idemplogada, idpedido);
        List<ItPedidoDto> itPedidoDtos=itens.stream().map(ItPedidoDto::new).toList();
        List<ItemDto> itensPedidoDto = new ArrayList<>();

        for (ItPedidoDto dto :  itPedidoDtos){
            ItemDto item = new ItemDto();
            item.setCdItemPedido(dto.cdItPedido());
            item.setQtd(dto.qtd());
            item.setCdProduto(dto.produto().getCdProduto().toString());
            item.setTotal(dto.vlUnit().multiply(BigDecimal.valueOf(dto.qtd())));
            item.setDsProduto(dto.produto().getDsProduto());
            item.setVlUnit(dto.vlUnit());
            itensPedidoDto.add(item);

        }
        return ResponseEntity.ok(itensPedidoDto);
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

    public List<ItemDto> localizarItensPedidoMesa(Long idemplogada, Long nrmesa) {
        List<ItPedido> itensPedido = repository.localizarItensPedidoMesa(idemplogada, nrmesa);
        List<ItemDto> listaItensProduto = new ArrayList<>();
        for (ItPedido it : itensPedido){
            ItemDto dto = new ItemDto();
            dto.setCdPedido(it.getPedido().getCdPedido());
            dto.setDsProduto(it.getProduto().getDsProduto());
            dto.setTotal(BigDecimal.valueOf(it.getQtd()).multiply(it.getVlUnit()));
            dto.setQtd(it.getQtd());
            dto.setCdProduto(it.getProduto().getCdProduto().toString());
            dto.setVlUnit(it.getVlUnit());
            dto.setCdItemPedido(it.getCdItPedido());
            dto.setDtInclusao(it.getDtInclusao().toString());
            listaItensProduto.add(dto);
        }
        return  listaItensProduto;
    }
}
