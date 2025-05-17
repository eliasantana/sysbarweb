package com.api.sysbarweb.controller;

import com.api.sysbarweb.dto.ItPedidoDto;
import com.api.sysbarweb.dto.ItemDto;
import com.api.sysbarweb.dto.PedidoDto;
import com.api.sysbarweb.services.PedidoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost")
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    PedidoServices services;


    @PostMapping("/adicionar/{idemplogada}/{idfuncionario}/{idemesa}")
    public ResponseEntity<PedidoDto> adicionar(@PathVariable  Long idemplogada,
                                               @PathVariable  Long idfuncionario,
                                               @PathVariable  Long idemesa,
                                               UriComponentsBuilder builder
    ){
       return services.adicionar(idemplogada, idfuncionario, idemesa, builder);
    }

    @GetMapping("/listar/{idemplogada}")
        public ResponseEntity<List<PedidoDto>>listar(@PathVariable Long idemplogada){
        return services.listar(idemplogada);
    }

    @GetMapping("/localizar/{idemplogada}/{idpedido}")
    public ResponseEntity<List<ItemDto>> localizar(@PathVariable Long idemplogada,
                                                 @PathVariable Long idpedido){
        return services.localizar(idemplogada, idpedido);

    }
    @GetMapping("/itens/{idemplogada}/{nrmesa}")
    public List<ItemDto> localizarItensPedidoMesa(@PathVariable Long idemplogada,
                                                  @PathVariable Long nrmesa){
        return services.localizarItensPedidoMesa(idemplogada, nrmesa);

    }
    @PostMapping("/fechar/{idemplogada}/{idpedido}/{idfuncionario}")
    public ResponseEntity<PedidoDto>fechar(@PathVariable Long idemplogada,
                                           @PathVariable Long idpedido,
                                           @PathVariable Long idfuncionario){

        return services.fecharPedido(idemplogada, idpedido, idfuncionario);
    }

    @PostMapping("/incluir/{idemlogada}/{idpedido}/{idproduto}/{qtd}")
    public ResponseEntity<ItPedidoDto>incluir(@PathVariable Long idemlogada,
                                              @PathVariable Long idpedido,
                                              @PathVariable Long idproduto,
                                              @PathVariable int  qtd,
                                              @RequestParam (name = "observacao", required = false) String observacao){
        return services.incluir(idemlogada, idpedido, idproduto,qtd,observacao);
    }

    @GetMapping("/listar/{idemplogada}/{idpedido}")
    public ResponseEntity<List<ItemDto>>listarItensPedido(@PathVariable Long idemplogada,
                                                          @PathVariable Long idpedido){
        return services.listarItensPedido(idemplogada, idpedido);
    }
    @PostMapping("/remover/{idemplogada}/{idpedido}")
    public ResponseEntity<ItPedidoDto>removerItemPedido(@PathVariable Long idemplogada,
                                                        @PathVariable Long idpedido,
                                                        @RequestParam(name = "passwordadm")  int pasword,
                                                        @RequestParam(name = "cditpedido")  Long cditem){

        return services.removeItemPedido(idemplogada, idpedido, pasword, cditem);
    }
    @PostMapping("/cancelar/{idemplogada}/{idpedido}")
    public ResponseEntity<PedidoDto>cancelarPedido(@PathVariable Long idemplogada,
                                                   @PathVariable Long idpedido){

        return services.cancelarPedido(idemplogada, idpedido);
    }

}
