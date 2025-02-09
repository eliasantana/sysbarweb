package com.api.sysbarweb.services;

import com.api.sysbarweb.dto.ItPedidoDto;
import com.api.sysbarweb.dto.PedidoDto;
import com.api.sysbarweb.exception.CaixaException;
import com.api.sysbarweb.exception.PedidoException;
import com.api.sysbarweb.exception.ProdutoException;
import com.api.sysbarweb.model.*;
import com.api.sysbarweb.repository.PedidoRepository;
import com.api.sysbarweb.repository.ProdutoEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
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

    @Autowired
    ProdutoEstoqueRepository produtoEstoqueRepository;

    @Autowired
    MovimentacaoServices movimentacaoServices;
    @Autowired
    CaixaServices caixaServices;

    @Autowired
    CozinhaServices cozinhaServices;
    public ResponseEntity<PedidoDto> adicionar(Long idemplogada,
                                               Long idfuncionario,
                                               Long idemesa,
                                               UriComponentsBuilder builder) {
       Optional<Empresa> emp =utilsServices.validaEmpresaLogada(idemplogada);
       List<Funcionario> func = utilsServices.validaFuncionario(idemplogada, idfuncionario);
       Optional<Mesa> mesa = utilsServices.retornaMesa(idemplogada, Math.toIntExact(idemesa));
        utilsServices.validaMesa(mesa);
        if (mesa.get().getStatus().equals("L")){
            mesa.get().setStatus("O"); // O = Ocupada
            mesaServices.mesaRepository.save(mesa.get());
        }
        Pedido p = new Pedido();
        p.setCdEmpresa(emp.get().getCdEmpresa());
        p.setCdFuncionario(func.get(0).getCdFuncionario());
        p.setCdMesa(mesa.get().getCdMesa());
        p.setStatusPedido("A");
        Pedido pedidosalvo = repository.save(p);
        URI uri = builder.path("/pedido/listar/{cdpedido}").buildAndExpand(new PedidoDto(pedidosalvo).cdPedido()).toUri();

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

    public ResponseEntity<PedidoDto> fecharPedido(Long idemplogada, Long idpedido, Long idfuncionario) {
        Optional<Pedido> pedido = utilsServices.validapedido(idemplogada, idpedido);
        Optional<Mesa> mesa= mesaServices.repository.existeMesa(pedido.get().getCdMesa());
        List<Funcionario> funcionario = utilsServices.validaFuncionario(idemplogada,idfuncionario);
        if (!funcionario.get(0).getCargo().getDsCargo().equals("Caixa")){
            throw new CaixaException("O funcionário não autorizado!");
        }else{
            Caixa caixaLocalizado = caixaServices.localizaCaixa(idemplogada, idfuncionario);
            BigDecimal totalPedido  = totalizapedido(idemplogada, pedido.get().getCdPedido());
            caixaLocalizado.setSaldoFinal(caixaLocalizado.getSaldoFinal().add(totalPedido));
            pedido.get().setStatusPedido("F");
            pedido.get().setTotalPedido(totalPedido);
            pedido.get().setCaixa(caixaLocalizado);
            PedidoDto dto = new PedidoDto(repository.save(pedido.get()));
            mesa.get().setStatus("L");
            mesaServices.mesaRepository.save(mesa.get());
            caixaServices.repository.save(caixaLocalizado);
            //TODO Gerar movimenação de caixa
        }
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

    public ResponseEntity<ItPedidoDto> incluir(Long idemlogada, Long idpedido, Long idproduto, int qtdSolicitada, String observacao) {
        Optional<Empresa> emp = utilsServices.validaEmpresaLogada(idemlogada);
        Optional<Pedido> pedido =utilsServices.validapedido(idemlogada, idpedido);
        Optional<ProdutoEstoque> produtoEstoque = utilsServices.validaNoProdutoEstoque(idemlogada, idproduto, qtdSolicitada);
        ProdutoEstoque pe = produtoEstoque.get();
        utilsServices.validaQuantidade(pe, qtdSolicitada);
        ItPedido itPedido = new ItPedido();
        itPedido.setQtd(qtdSolicitada);
        itPedido.setPedido(pedido.get());
        itPedido.setProduto(produtoEstoque.get().getProduto());
        itPedido.setVlUnit(produtoEstoque.get().getVlVenda());
        ItPedido item = itPedidoServices.adicioar(itPedido);
        int qtd = produtoEstoque.get().getQtd() - itPedido.getQtd();
        produtoEstoque.get().setQtd(qtd);
        ProdutoEstoque produtoEstoqueSalvo = produtoEstoqueRepository.save(produtoEstoque.get());
        //Registra a movimentação
        if (produtoEstoqueSalvo.getCdProdutoEstoque()!=null){
            Movimentacao movimentacao = new Movimentacao();
            movimentacao.setDsProduto(produtoEstoque.get().getProduto().getDsProduto());
            movimentacao.setCdProduto(produtoEstoque.get().getProduto().getCdProduto());
            movimentacao.setCdPedido(idpedido);
            movimentacao.setQtd(qtdSolicitada);
            movimentacao.setTpMovimentacao("S");
            movimentacao.setCdEstoque(produtoEstoqueSalvo.getEstoque().getCdEstoque());
            movimentacao.setCdProdutoEstoque(produtoEstoqueSalvo.getCdProdutoEstoque());
            movimentacaoServices.adicionaMovimentacao(movimentacao);
        }
        if (utilsServices.getChave("SN_ENVIA_COZINHA_AUTO").equalsIgnoreCase("S")){
            List<Funcionario> funcionario = utilsServices.validaFuncionario(idemlogada, pedido.get().getCdFuncionario());
            Optional<Mesa> mesa= mesaServices.getMesa(pedido.get().getCdMesa());
            if (produtoEstoqueSalvo.getProduto().getTipo().equalsIgnoreCase("CO")){
               Cozinha c = new Cozinha();
               c.setCdProduto(idproduto);
               //c.setCdFuncionario(pedido.get().getCdFuncionario());
               c.setCdPedido(idpedido);
               c.setHoraSolicitacao(LocalDateTime.now());
               //c.setNmFuncionario(funcionario.get(0).getNome());
               c.setStatus("P");
               c.setNrMesa(mesa.get().getNrMesa());
               c.setQtd(itPedido.getQtd());
               c.setObservacao(observacao);
               c.setCdEmpresa(idemlogada);
               c.setNmPrato(produtoEstoqueSalvo.getProduto().getDsProduto());
               cozinhaServices.repository.save(c);
            }
        }
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<ItPedidoDto>> listarItensPedido(Long idemplogada, Long idpedido) {
        return itPedidoServices.localizar(idemplogada,idpedido);
    }

    public ResponseEntity<ItPedidoDto> removeItemPedido(Long idemplogada, Long idpedido, int pasword, Long cdItPedido) {
        List<ItPedido> itensPedido;
        if (utilsServices.validaSenhaAdministrativa(pasword)) {
            Optional<Empresa> empresa = utilsServices.validaEmpresaLogada(idemplogada);
            Optional<Pedido> pedido = utilsServices.validapedido(idemplogada, idpedido);
            itensPedido = itPedidoServices.validaItemPedido(idemplogada, idpedido, cdItPedido);
            Optional<Movimentacao> movimentacaoLocalizada = movimentacaoServices.localizaMovimentacao(pedido.get().getCdPedido(), itensPedido.get(0).getProduto().getCdProduto(), itensPedido.get(0).getQtd());

            Optional<ProdutoEstoque> produtoEstoque = produtoEstoqueRepository.validaProdutoEstoque(movimentacaoLocalizada.get().getCdEstoque(), movimentacaoLocalizada.get().getCdProduto());
            //Devolvendo a quantidade da moviemntação para o estoque
            produtoEstoque.get().setQtd(produtoEstoque.get().getQtd() + movimentacaoLocalizada.get().getQtd());
            ProdutoEstoque pe = produtoEstoqueRepository.save(produtoEstoque.get());
            //Registrar movimentação de Devolução "D"
            itPedidoServices.repository.delete(itensPedido.get(0));
            Movimentacao m = new Movimentacao();
            m.setCdProdutoEstoque(pe.getCdProdutoEstoque());
            m.setQtd(movimentacaoLocalizada.get().getQtd());
            m.setTpMovimentacao("D"); // D - Devolução
            m.setCdPedido(movimentacaoLocalizada.get().getCdPedido());
            m.setDsProduto(movimentacaoLocalizada.get().getDsProduto());
            m.setCdProduto(pe.getProduto().getCdProduto());
            m.setCdEstoque(pe.getEstoque().getCdEstoque());
            movimentacaoServices.repository.save(m);
            movimentacaoServices.repository.delete(movimentacaoLocalizada.get());
            //Registram em log
        } else {
            throw new ProdutoException("Senha inválida!");
        }
        return ResponseEntity.ok(new ItPedidoDto(itensPedido.get(0)));
    }

    public ResponseEntity<PedidoDto> cancelarPedido(Long idemplogada, Long idpedido) {
        Optional<Pedido> p = utilsServices.validapedido(idemplogada,idpedido);
        List<ItPedido> itensPedido= itPedidoServices.repository.localizar(idemplogada, idpedido);
        if (!itensPedido.isEmpty()){
            throw new PedidoException(String.format("O pedido %s não pode ser cancelado porque possui itens associados. Remova todos os itens antes de cancelar.",idpedido));
        }
        p.get().setStatusPedido("F");
        Pedido pedidoSalvo = repository.save(p.get());
        Mesa m = new Mesa();
        m.setCdMesa(pedidoSalvo.getCdMesa());
        m.setStatus("L");
        mesaServices.repository.save(m);
        return ResponseEntity.ok(new PedidoDto(p.get()));
    }
}
