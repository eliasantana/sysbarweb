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
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public ResponseEntity<List<ItemDto>> localizarProdutoAgregado(Long idemplogada, Long idpedido) {
        Optional<Empresa> emp = utilsServices.validaEmpresaLogada(idemplogada);
        Optional<Pedido> pedido =  utilsServices.validapedido(idemplogada, idpedido);
        List<ItPedido> itens =repository.localizar(idemplogada, idpedido);

        // Converte a lista de ItPedido para ItPedidoDto
        List<ItPedidoDto> itPedidoDtos = itens.stream().map(ItPedidoDto::new).toList();

        // 1. Agrupar por Produto (cdProduto) e somar a quantidade
        Map<String, ItPedidoDto> agrupados = itPedidoDtos.stream()
                .collect(Collectors.toMap(
                        dto -> dto.produto().getCdProduto().toString(), // Chave: Código do Produto
                        Function.identity(), // Valor inicial: o próprio DTO
                        (dto1, dto2) -> { // Função de merge: soma as quantidades e mantém os outros dados
                            int novaQtd = dto1.qtd() + dto2.qtd();
                            // Assumindo que os outros campos (como vlUnit, dsProduto) são os mesmos para o mesmo produto
                            // Se precisar de um novo objeto ItPedidoDto, você deve criá-lo aqui.
                            // Como ItPedidoDto é um record/DTO imutável, é comum usar o DTO do primeiro item
                            // e apenas atualizar a quantidade.
                            // Se a classe ItPedidoDto tiver um construtor que aceite a nova quantidade, use-o.
                            // Exemplo: return new ItPedidoDto(dto1.cdItPedido(), novaQtd, dto1.vlUnit(), ...);
                            // Já que não temos o código completo do ItPedidoDto, vamos simular que ele pode ter a qtd somada
                            // ATENÇÃO: Se ItPedidoDto for imutável (record), isso precisará ser ajustado.
                            // Vou criar um Map intermediário com os campos necessários para simplificar a demonstração.
                            return dto1; // Apenas para o compilador, será reescrito na próxima etapa.
                        }
                ));

        // Devido à complexidade de somar QTD em um objeto imutável (DTO/Record),
        // é mais claro e seguro usar Collectors.groupingBy e depois mapear.

        Map<String, Integer> produtoQtdSum = itPedidoDtos.stream()
                .collect(Collectors.groupingBy(
                        dto -> dto.produto().getCdProduto().toString(), // Chave: Código do Produto
                        Collectors.summingInt(ItPedidoDto::qtd) // Valor: Soma das quantidades
                ));

        // Agora, para criar a lista de ItemDto, você itera sobre os agrupamentos.
        List<ItemDto> itensPedidoDto = new ArrayList<>();

        // 2. Mapear o Map de soma para a lista final de ItemDto
        itPedidoDtos.stream()
                .collect(Collectors.toMap(
                        dto -> dto.produto().getCdProduto().toString(), // Chave: Código do Produto
                        Function.identity(), // Valor: O DTO completo
                        (dto1, dto2) -> dto1 // Pega apenas o primeiro DTO completo para obter dsProduto, vlUnit, etc.
                ))
                .forEach((cdProduto, dto) -> {
                    ItemDto item = new ItemDto();

                    // Usamos a quantidade **somada** do Map produtoQtdSum
                    int qtdSomada = produtoQtdSum.get(cdProduto);

                    // Os outros dados (descrição, valor unitário) são tirados do primeiro DTO encontrado (dto)
                    item.setCdItemPedido(dto.cdItPedido()); // Manter o cdItemPedido do primeiro item (pode ser irrelevante)
                    item.setQtd(qtdSomada); // <<-- Quantidade SOMADA
                    item.setCdProduto(cdProduto);

                    // Recalcula o total com a quantidade somada
                    item.setTotal(dto.vlUnit().multiply(BigDecimal.valueOf(qtdSomada)));

                    item.setDsProduto(dto.produto().getDsProduto());
                    item.setVlUnit(dto.vlUnit());

                    itensPedidoDto.add(item);
                });

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
