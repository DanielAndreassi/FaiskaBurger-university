package com.faiskaburgers.faiskaburger.database.dal;

import com.faiskaburgers.faiskaburger.database.entity.Produto;
import com.faiskaburgers.faiskaburger.database.util.SingletonDB;

import java.util.List;

public class ProdutoDAL implements IDAL<Produto> {

    @Override
    public boolean gravar(Produto entidade) {
        String sql = "INSERT INTO produto (produto_nome,produto_desc,produto_valor,categoria_id) VALUES ('#1','#2',#3,#4)";
        sql = sql.replace("#1",entidade.getNomeProduto());
        sql = sql.replace("#2",entidade.getDescricaoProduto());
        sql = sql.replace("#3",""+entidade.getValorProduto());
        sql = sql.replace("#4",""+entidade.getCategoria().getIdCategoria());
        return SingletonDB.getConexao().manipular(sql);
    }

    @Override
    public boolean alterar(Produto entidade) {
        String sql = "UPDATE produto SET produto_nome='#1',produto_desc='#2',produto_valor=#3,categoria_id=#4)" +
                "WHERE produto_id = #5";
        sql = sql.replace("#1",entidade.getNomeProduto());
        sql = sql.replace("#2",entidade.getDescricaoProduto());
        sql = sql.replace("#3",""+entidade.getValorProduto());
        sql = sql.replace("#4",""+entidade.getCategoria().getIdCategoria());
        sql = sql.replace("#5",""+entidade.getIdProduto());
        return SingletonDB.getConexao().manipular(sql);
    }

    @Override
    public boolean apagar(Produto entidade) {
        return SingletonDB.getConexao().manipular("DELETE FROM produto WHERE produto_id ="+entidade.getIdProduto());
    }

    @Override
    public Produto get(int id) {
        return null;
    }

    @Override
    public List<Produto> get(String filtro) {
        return List.of();
    }
}
