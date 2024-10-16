package com.faiskaburgers.faiskaburger.database.dal;

import com.faiskaburgers.faiskaburger.database.entity.Categoria;
import com.faiskaburgers.faiskaburger.database.entity.Produto;
import com.faiskaburgers.faiskaburger.database.util.SingletonDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String sql = "SELECT * FROM produto WHERE produto_id ="+id;
        Produto produto = new Produto();
        ResultSet resultSet = SingletonDB.getConexao().consultar(sql);

        try {
            produto.setIdProduto(resultSet.getInt("produto_id"));
            produto.setNomeProduto(resultSet.getString("produto_nome"));
            produto.setDescricaoProduto(resultSet.getString("produto_desc"));
            produto.setValorProduto(resultSet.getDouble("produto_valor"));
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(resultSet.getInt("categoria_id"));
            produto.setCategoria(categoria);

        }
        catch (SQLException e) {e.printStackTrace();}

        return produto;
    }

    @Override
    public List<Produto> get(String filtro) {
        List <Produto> produtos = new ArrayList<>();

        String sql = "SELECT * FROM produto";
        if(!filtro.isEmpty())
            sql += " WHERE " + filtro;

        System.out.println("SQL Query: " + sql);
        ResultSet resultSet = SingletonDB.getConexao().consultar(sql);
        try {
            while (resultSet.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(resultSet.getInt("categoria_id"));
                Produto produto = new Produto(resultSet.getString("produto_nome"),
                        resultSet.getString("produto_desc"),
                        resultSet.getDouble("produto_valor"),
                        categoria
                );
                produtos.add(produto);
            }
        }catch (SQLException e) {e.printStackTrace();}

        return produtos;
    }
}
