package com.api.sysbarweb.controller;

import com.api.sysbarweb.dto.ItPedidoDto;
import com.api.sysbarweb.dto.ItemDto;
import com.api.sysbarweb.dto.PedidoDto;
import com.api.sysbarweb.services.PedidoServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/pedido")
@Tag(name = "Pedido", description = "Gerenciamento e Controle de Pedidos")
public class PedidoController {

    @Autowired
    PedidoServices services;

    @Operation(summary = "Cria um pedido")
    @PostMapping("/adicionar/{idemplogada}/{idfuncionario}/{idemesa}")
    public ResponseEntity<PedidoDto> adicionar(@PathVariable  Long idemplogada,
                                               @PathVariable  Long idfuncionario,
                                               @PathVariable  Long idemesa,
                                               UriComponentsBuilder builder
    ){
       return services.adicionar(idemplogada, idfuncionario, idemesa, builder);
    }

    @Operation(summary = "Listar Pedidos Abertos por Empresa")
    @GetMapping("/listar/{idemplogada}")
        public ResponseEntity<List<PedidoDto>>listar(@PathVariable Long idemplogada){
        return services.listar(idemplogada);
    }

    @Operation(summary = "Listar Itens do Pedido pelo Id do Pedido")
    @GetMapping("/localizar/{idemplogada}/{idpedido}")
    public ResponseEntity<List<ItemDto>> localizar(@PathVariable Long idemplogada,
                                                 @PathVariable Long idpedido){
        return services.localizar(idemplogada, idpedido);

    }

    @Operation(summary = "Localiza um Pedido e Retorna seus Itens")
    @GetMapping("/itens/{idemplogada}/{nrmesa}")
    public List<ItemDto> localizarItensPedidoMesa(@PathVariable Long idemplogada,
                                                  @PathVariable Long nrmesa){
        return services.localizarItensPedidoMesa(idemplogada, nrmesa);

    }
    @Operation(summary = "Fecha um peidido")
    @PostMapping("/fechar/{idemplogada}/{idpedido}/{idfuncionario}/{formapagto}")
    public ResponseEntity<PedidoDto>fechar(@PathVariable Long idemplogada,
                                           @PathVariable Long idpedido,
                                           @PathVariable Long idfuncionario,
                                           @PathVariable String formapagto){

        return services.fecharPedido(idemplogada, idpedido, idfuncionario, formapagto);
    }

    @Operation(summary = "Adiciona um Produto ao Pedido")
    @PostMapping("/incluir/{idemlogada}/{idpedido}/{idproduto}/{qtd}")
    public ResponseEntity<ItPedidoDto>incluir(@PathVariable Long idemlogada,
                                              @PathVariable Long idpedido,
                                              @PathVariable Long idproduto,
                                              @PathVariable int  qtd,
                                              @RequestParam (name = "observacao", required = false) String observacao){
        return services.incluir(idemlogada, idpedido, idproduto,qtd,observacao);
    }

    @Operation(summary = "Listar Itens do Pedido")
    @GetMapping("/listar/{idemplogada}/{idpedido}")
    public ResponseEntity<List<ItemDto>>listarItensPedido(@PathVariable Long idemplogada,
                                                          @PathVariable Long idpedido){
        return services.listarItensPedido(idemplogada, idpedido);
    }

    @Operation(summary = "Remover Item do Pedido")
    @PostMapping("/remover/{idemplogada}/{idpedido}")
    public ResponseEntity<ItPedidoDto>removerItemPedido(@PathVariable Long idemplogada,
                                                        @PathVariable Long idpedido,
                                                        @RequestParam(name = "passwordadm")  int pasword,
                                                        @RequestParam(name = "cditpedido")  Long cditem){

        return services.removeItemPedido(idemplogada, idpedido, pasword, cditem);
    }

    @Operation(summary = "Cancelar Pedido")
    @PostMapping("/cancelar/{idemplogada}/{idpedido}")
    public ResponseEntity<PedidoDto>cancelarPedido(@PathVariable Long idemplogada,
                                                   @PathVariable Long idpedido){

        return services.cancelarPedido(idemplogada, idpedido);
    }

    @Operation(summary = "Localiza um Pedido pelo Número da Mesa")
    @GetMapping("/localizarpedido/{cdemplogada}/{nrmesa}")
    public ResponseEntity<PedidoDto>localizaPedidoPorNumeroMesa(@PathVariable Long cdemplogada,
                                                                @PathVariable Long nrmesa){
        return services.localizaPedidoPorNumeroMesa(nrmesa,cdemplogada);
    }


}
