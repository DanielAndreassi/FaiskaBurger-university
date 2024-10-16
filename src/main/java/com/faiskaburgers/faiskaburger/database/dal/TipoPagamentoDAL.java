package com.faiskaburgers.faiskaburger.database.dal;

import com.faiskaburgers.faiskaburger.database.entity.TipoPagamento;
import com.faiskaburgers.faiskaburger.database.util.SingletonDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoPagamentoDAL implements IDAL<TipoPagamento> {


    @Override
    public boolean gravar(TipoPagamento entidade) {

        String sql = "INSERT INTO tipo_pagamento (tipo_pagamento_nome) VALUES (#1)";
        sql = sql.replace("#1", entidade.getTipoPagamento());

        return SingletonDB.getConexao().manipular(sql);
    }

    @Override
    public boolean alterar(TipoPagamento entidade) {
        String sql = "UPDATE tipo_pagamento SET tipo_pagamento_nome = #1 WHERE tipo_pagamento_id = #2";
        sql = sql.replace("#1", entidade.getTipoPagamento());
        sql = sql.replace("#2", ""+entidade.getIdTipoPagamento());

        return SingletonDB.getConexao().manipular(sql);
    }

    @Override
    public boolean apagar(TipoPagamento entidade) {
        return SingletonDB.getConexao().manipular("DELETE FROM tipo_pagamento WHERE tipo_pagamento_id ="+entidade.getIdTipoPagamento());
    }

    @Override
    public TipoPagamento get(int id) {
        String sql = "SELECT * FROM tipo_pagamento WHERE tipo_pagamento_id ="+id;
        TipoPagamento tipoPagamento = null;
        ResultSet resultSet = SingletonDB.getConexao().consultar(sql);

        try {
            if(resultSet.next()) {
                tipoPagamento = new TipoPagamento(resultSet.getString("tipo_pagamento_nome"));
            }
        } catch (SQLException e) {e.printStackTrace();}

        return tipoPagamento;
    }

    @Override
    public List<TipoPagamento> get(String filtro) {
        List<TipoPagamento> tiposPagamento = new ArrayList<>();
        String sql = "SELECT * FROM tipo_pagamento";

        if(!filtro.isEmpty())
            sql = sql + " WHERE " + filtro;

        ResultSet resultSet = SingletonDB.getConexao().consultar(sql);
        try {
            while(resultSet.next()) {
                TipoPagamento tipoPagamento = new TipoPagamento(resultSet.getString("tipo_pagamento_nome"),
                        resultSet.getInt("tipo_pagamento_id"));
                tiposPagamento.add(tipoPagamento);
            }
        }catch (SQLException e) {e.printStackTrace();}

        return tiposPagamento;
    }
}
