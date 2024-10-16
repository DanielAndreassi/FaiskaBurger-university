package com.faiskaburgers.faiskaburger;

import com.faiskaburgers.faiskaburger.database.dal.CategorialDAL;
import com.faiskaburgers.faiskaburger.database.dal.ProdutoDAL;
import com.faiskaburgers.faiskaburger.database.entity.Categoria;
import com.faiskaburgers.faiskaburger.database.entity.Produto;
import com.faiskaburgers.faiskaburger.database.util.SingletonDB;

import java.util.List;

public class MainTeste {
    public static void main(String[] args) {
        if(!SingletonDB.conectar()) System.out.println("Erro: "+SingletonDB.getConexao().getMensagemErro());
//        CategorialDAL categorialDAL = new CategorialDAL();
//        System.out.println("Classe de Teste");
//        Produto produto = new Produto("x-tudo","lanche com tudao",30,categorialDAL.get(4));
        ProdutoDAL produtoDAL = new ProdutoDAL();
//
//
//        if(produtoDAL.gravar(produto)) System.out.println("Produto gravada com sucesso");
//
//        else System.out.println("Erro: "+ SingletonDB.getConexao().getMensagemErro());
//
//        System.out.println("Produto: "+ produto);

        List<Produto> produtosList = produtoDAL.get("");
        System.out.println(produtosList);
    }
}
