package com.faiskaburgers.faiskaburger.database.dal;

import com.faiskaburgers.faiskaburger.database.entity.Pedido;
import com.faiskaburgers.faiskaburger.database.entity.Produto;
import com.faiskaburgers.faiskaburger.database.util.SingletonDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PedidoDAL implements IDAL<Pedido> {

    @Override
    public boolean gravar(Pedido entidade) {
        boolean erro = false;
        try {
            SingletonDB.getConexao().getConnect().setAutoCommit(false);

            String sql = "INSERT INTO pedido (pedido_data,pedido_cli_nome,pedido_cli_fone,pedido_vTotal,pedido_viagem,tipo_pagamento_id)" +
                    "VALUES ('#1','#2','#3',#4,'#5',#6)";

            sql = sql.replace("#1", entidade.getDataPedido().toString());
            sql = sql.replace("#2", entidade.getNomeCliente());
            sql = sql.replace("#3", entidade.getFoneCliente());
            sql = sql.replace("#4", "" + entidade.getTotalPedido());
            sql = sql.replace("#5", "" + entidade.getViagem());
            sql = sql.replace("#6", "" + entidade.getTipoPagamento().getIdTipoPagamento());
            sql = sql.replace("#7", "" + entidade.getIdPedido());

            if (SingletonDB.getConexao().manipular(sql)) {

                int id = SingletonDB.getConexao().getMaxPK("pedido", "pedido_id");
                for (Pedido.Item item : entidade.getItens()) {
                    String sql2 = "INSERT INTO item(produto_id,pedido_id,item_qtde,item_valor)" +
                            "VALUES (#1,#2,#3,#4);";
                    sql2 = sql2.replace("#1", "" + item.produto().getIdProduto());
                    sql2 = sql2.replace("#2", "" + entidade.getIdPedido());
                    sql2 = sql2.replace("#3", "" + item.quantidade());
                    sql2 = sql2.replace("#4", "" + item.valor());

                    if (!SingletonDB.getConexao().manipular(sql2))
                        erro = true;
                }
            } else erro = true;

            if (erro) {
                SingletonDB.getConexao().getConnect().rollback();
            } else SingletonDB.getConexao().getConnect().commit();

            SingletonDB.getConexao().getConnect().setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !erro;
    }

    @Override
    public boolean alterar(Pedido entidade) {
        boolean erro = false;
        try {
            SingletonDB.getConexao().getConnect().setAutoCommit(false);

            String sql = "UPDATE pedido SET pedido_data,pedido'#1'_cli_nome='#2',pedido_cli_fone='#3',pedido_vTotal=#4,pedido_viagem='#5',tipo_pagamento_id=#6" +
                    "WHERE pedido_id=#7";
            sql = sql.replace("#1", entidade.getDataPedido().toString());
            sql = sql.replace("#2", entidade.getNomeCliente());
            sql = sql.replace("#3", entidade.getFoneCliente());
            sql = sql.replace("#4", "" + entidade.getTotalPedido());
            sql = sql.replace("#5", "" + entidade.getViagem());
            sql = sql.replace("#6", "" + entidade.getTipoPagamento().getIdTipoPagamento());

            if (SingletonDB.getConexao().manipular(sql)) {

                SingletonDB.getConexao().manipular("DELETE FROM item WHERE pedido_id=" + entidade.getIdPedido());
                for (Pedido.Item item : entidade.getItens()) {
                    String sql2 = "INSERT INTO item(produto_id,pedido_id,item_qtde,item_valor)" +
                            "VALUES (#1,#2,#3,#4);";
                    sql2 = sql2.replace("#1", "" + item.produto().getIdProduto());
                    sql2 = sql2.replace("#2", "" + entidade.getIdPedido());
                    sql2 = sql2.replace("#3", "" + item.quantidade());
                    sql2 = sql2.replace("#4", "" + item.valor());

                    if (!SingletonDB.getConexao().manipular(sql2))
                        erro = true;
                }
            } else erro = true;

            if (erro) {
                SingletonDB.getConexao().getConnect().rollback();
            } else SingletonDB.getConexao().getConnect().commit();

            SingletonDB.getConexao().getConnect().setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !erro;
    }

    @Override
    public boolean apagar(Pedido entidade) {
        return SingletonDB.getConexao().manipular("DELETE FROM item WHERE pedido_id="+entidade.getIdPedido());
    }

    @Override
    public Pedido get(int id) {
        Pedido pedido = null;

        String sql = "SELECT * FROM pedido WHERE pedido_id="+id;
        try {
            ResultSet resultset = SingletonDB.getConexao().consultar(sql);
            if(resultset.next()) {
                pedido = new Pedido(resultset.getInt("pedido+id"),
                        resultset.getDate("pedido_data").toLocalDate(),
                        resultset.getString("pedido_cli_nome"),
                        resultset.getString("pedido_cli_fone"),
                        resultset.getDouble("pedido_vTotal"),
                        resultset.getString("pedido_viagem").charAt(0),
                        new TipoPagamentoDAL().get(resultset.getInt("tipo_pagamento_id")));
            }
            String sql2 = "SELECT * FROM item WHERE pedido_id="+pedido.getIdPedido();
            ResultSet resultset2 = SingletonDB.getConexao().consultar(sql2);

            while(resultset2.next()) {
                Produto produto = new ProdutoDAL().get(resultset2.getInt("produto_id"));
                produto.setValorProduto(resultset2.getDouble("item_valor"));
                pedido.addItem(new ProdutoDAL().get(resultset2.getInt("produto_id")),
                        resultset2.getInt("item_qtde"));
            }
        } catch (Exception e) {e.printStackTrace();}
        return pedido;
    }

    @Override
    public List<Pedido> get(String filtro) {
        List<Pedido> pedidos = null;
        Pedido pedido = null;

        String sql = "SELECT * FROM pedido";
        if(!filtro.isEmpty()) sql+=" WHERE "+filtro;
        try {
            ResultSet resultset = SingletonDB.getConexao().consultar(sql);
            while(resultset.next()) {
                pedido = new Pedido(resultset.getInt("pedido+id"),
                        resultset.getDate("pedido_data").toLocalDate(),
                        resultset.getString("pedido_cli_nome"),
                        resultset.getString("pedido_cli_fone"),
                        resultset.getDouble("pedido_vTotal"),
                        resultset.getString("pedido_viagem").charAt(0),
                        new TipoPagamentoDAL().get(resultset.getInt("tipo_pagamento_id")));
                pedidos.add(pedido);
            }
            String sql2 = "SELECT * FROM item WHERE pedido_id="+pedido.getIdPedido();
            ResultSet resultset2 = SingletonDB.getConexao().consultar(sql2);

            while(resultset2.next()) {
                Produto produto = new ProdutoDAL().get(resultset2.getInt("produto_id"));
                produto.setValorProduto(resultset2.getDouble("item_valor"));

                pedido.addItem(new ProdutoDAL().get(resultset2.getInt("produto_id")),
                        resultset2.getInt("item_qtde"));
            }
        } catch (Exception e) {e.printStackTrace();}
        return pedidos;

    }
}
