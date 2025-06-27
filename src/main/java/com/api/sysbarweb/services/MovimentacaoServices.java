package com.api.sysbarweb.services;

import com.api.sysbarweb.exception.ProdutoEstoqueException;
import com.api.sysbarweb.model.Movimentacao;
import com.api.sysbarweb.model.ProdutoEstoque;
import com.api.sysbarweb.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovimentacaoServices {
    @Autowired
    MovimentacaoRepository repository;

    public void adicionaMovimentacao(ProdutoEstoque produtoEstoque, int qtd,  String tpMoviemtnacao) {
        Movimentacao m = new Movimentacao();
        m.setCdEstoque(produtoEstoque.getCdProdutoEstoque());
        m.setTpMovimentacao(tpMoviemtnacao);
        m.setQtd(qtd);
        m.setCdEstoque(produtoEstoque.getEstoque().getCdEstoque());
        m.setCdProduto(produtoEstoque.getProduto().getCdProduto());
        m.setDsProduto(produtoEstoque.getProduto().getDsProduto());
        repository.save(m);
    }
    public void adicionaMovimentacao(Movimentacao m) {
        repository.save(m);
    }

    public Optional<Movimentacao> localizaMovimentacao(Long cdPedido, Long idproduto, int qtd, Long cdItPedido) {
        Optional<Movimentacao>movimentacao = repository.localizarMovimentacao(cdPedido,idproduto,qtd,cdItPedido );
        if (movimentacao.isEmpty()){
            throw new ProdutoEstoqueException(String.format("Não foi possível localizar a movimentação do pedido %s para o item %s e quantidade %s.", cdPedido, idproduto, qtd));
        }
        return movimentacao ;
    }
}
