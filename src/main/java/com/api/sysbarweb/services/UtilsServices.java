package com.api.sysbarweb.services;

import com.api.sysbarweb.exception.EmpresaException;
import com.api.sysbarweb.exception.ProdutoException;
import com.api.sysbarweb.model.Empresa;
import com.api.sysbarweb.model.Estoque;
import com.api.sysbarweb.model.Produto;
import com.api.sysbarweb.repository.EmpresaRepository;
import com.api.sysbarweb.repository.EstoqueRepository;
import com.api.sysbarweb.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UtilsServices {

    @Autowired
    FuncionarioServices funcionarioServices;

    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    EstoqueRepository estoqueRepository;

    @Autowired
    ProdutoRepository produtoRepository;




    public Optional<Empresa> validaEmpresaLogada(Long idemplogada){
        Optional<Empresa> empresaLocalizada = empresaRepository.getEmpresa(idemplogada);
       if (empresaLocalizada.isEmpty()){
           throw new EmpresaException("A empresa iformada não foi localizada!");
       }
       return empresaLocalizada;
    }

    public Optional<Estoque>  validaEstoque(Long idestoque, Long idemplogada){
        Optional<Estoque> estoqueLocalizado = estoqueRepository.localizar(idemplogada, idestoque);
        if (estoqueLocalizado.isEmpty()){
            throw new EmpresaException("O Estoque Informado não foi localizado na empresa logada!");
        }
        return estoqueLocalizado;
    }

    /**
     * Valida se o produto informado existe cadastrado na base
     * */
    public Optional<Produto>validaProduto(Long idemplogada, Long idproduto){
        Optional<Produto> produto =produtoRepository.localizar(idemplogada, idproduto);
        if (produto.isEmpty()){
            throw new ProdutoException("O produto informado não foi lodalizado!");
        }
       return produto;
    }
}
