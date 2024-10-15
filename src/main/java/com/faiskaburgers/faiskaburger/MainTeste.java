package com.faiskaburgers.faiskaburger;

import com.faiskaburgers.faiskaburger.database.dal.CategorialDAL;
import com.faiskaburgers.faiskaburger.database.entity.Categoria;
import com.faiskaburgers.faiskaburger.database.util.SingletonDB;

import java.util.List;

public class MainTeste {
    public static void main(String[] args) {
        if(!SingletonDB.conectar()) System.out.println("Erro: "+SingletonDB.getConexao().getMensagemErro());

        System.out.println("Classe de Teste");
        Categoria categoria = new Categoria("Refrigerante");
        CategorialDAL categorialDAL = new CategorialDAL();

        if(categorialDAL.gravar(categoria)) System.out.println("Categoria gravada com sucesso");

        else System.out.println("Erro: "+ SingletonDB.getConexao().getMensagemErro());

        Categoria categorias = categorialDAL.get(1);
        categorias.setNomeCategoria(categoria.getNomeCategoria().toUpperCase());
        categorialDAL.alterar(categorias);
        categorialDAL.apagar(categoria);
        List<Categoria> categoriaList =categorialDAL.get("");
        System.out.println(categoriaList);
    }
}
