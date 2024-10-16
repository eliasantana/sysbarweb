package com.api.sysbarweb.services;

import com.api.sysbarweb.exception.EmpresaException;
import com.api.sysbarweb.exception.FuncionarioException;
import com.api.sysbarweb.exception.MesaException;
import com.api.sysbarweb.exception.ProdutoException;
import com.api.sysbarweb.model.*;
import com.api.sysbarweb.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


@Service
public class UtilsServices {

    @Autowired
    FuncionarioResponsitory funcionarioResponsitory;

    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    EstoqueRepository estoqueRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    MesaRepository mesaRepository;


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

    public List<Funcionario> validaFuncionario(Long idEmpLogada, Long idfuncionario) {
       List<Funcionario> f= funcionarioResponsitory.localizarFuncionario(idEmpLogada,idfuncionario);
       if (f.isEmpty()){
           throw new FuncionarioException("O funcionário informado não existe!");
       }else{
           return f;
       }
    }

    public boolean existMesa(List<Mesa> mesas, int nrMesa) {
        Boolean resp = false;
        for (int i = 0; i < mesas.size() ; i++) {
            if (mesas.get(i).getNrMesa()==nrMesa){
                resp=true;
            }
        }
        return resp;
    }

    public Boolean validaMesa(Long idemplogada, int nrmesa){
        Boolean resp = false;
       Optional<Mesa> m= mesaRepository.existeMesa(idemplogada, nrmesa);
       if(m.isEmpty()){
           throw  new MesaException("A mesa infomradanão exise na empresa logada!");
       }else{
           resp=true;
       }
       return resp;
    }
}
