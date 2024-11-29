package com.faiskaburgers.faiskaburger.database.dal;

import com.faiskaburgers.faiskaburger.database.entity.Empresa;
import com.faiskaburgers.faiskaburger.database.util.SingletonDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDAL implements IDAL<Empresa> {

    @Override
    public boolean gravar(Empresa entidade) {
        String sql = "INSERT INTO empresa (empresa_razao,empresa_fantasia,empresa_cnpj,empresa_cep," +
                "empresa_rua,empresa_numero,empresa_bairro,empresa_cidade,empresa_uf,empresa_fone,empresa_email,empresa_valor_embalagem " +
                "VALUES ('#1','#2','#3','#4','#5','#6','#7','#8','#9','#10','#11',#12";

        sql = sql.replace("#1",entidade.getRazao_social());
        sql = sql.replace("#2",entidade.getFantasia());
        sql = sql.replace("#3",entidade.getCnpj());
        sql = sql.replace("#4",entidade.getCep());
        sql = sql.replace("#5",entidade.getRua());
        sql = sql.replace("#6",entidade.getNumero());
        sql = sql.replace("#7",entidade.getBairro());
        sql = sql.replace("#8",entidade.getCidade());
        sql = sql.replace("#9",entidade.getUF());
        sql = sql.replace("#10",entidade.getTelefone());
        sql = sql.replace("#11",entidade.getEmail());
        sql = sql.replace("#12",""+entidade.getValor_embalagem());

        return SingletonDB.getConexao().manipular(sql);
    }
    @Override
    public boolean alterar(Empresa entidade) {
        String sql = "UPDATE empresa SET empresa_razao='#1',empresa_fantasia='#2',empresa_cnpj='#3',empresa_cep='#4'," +
                "empresa_rua='#5',empresa_numero='#6',empresa_bairro='#7',empresa_cidade='#8',empresa_uf='#9'," +
                "empresa_fone='#10',empresa_email='#11',empresa_valor_embalagem=#12 WHERE empresa_id=#13";

        sql = sql.replace("#1",entidade.getRazao_social());
        sql = sql.replace("#2",entidade.getFantasia());
        sql = sql.replace("#3",entidade.getCnpj());
        sql = sql.replace("#4",entidade.getCep());
        sql = sql.replace("#5",entidade.getRua());
        sql = sql.replace("#6",entidade.getNumero());
        sql = sql.replace("#7",entidade.getBairro());
        sql = sql.replace("#8",entidade.getCidade());
        sql = sql.replace("#9",entidade.getUF());
        sql = sql.replace("#10",entidade.getTelefone());
        sql = sql.replace("#11",entidade.getEmail());
        sql = sql.replace("#12",""+entidade.getValor_embalagem());
        sql = sql.replace("#13",""+entidade.getEmpresa_id());

        return SingletonDB.getConexao().manipular(sql);
    }

    @Override
    public boolean apagar(Empresa entidade) {
        return SingletonDB.getConexao().manipular("DELETE FROM empresa WHERE empresa_id="+entidade.getEmpresa_id());
    }

    @Override
    public Empresa get(int id) {
        String sql = "SELECT * FROM empresa WHERE empresa_id="+id;
        Empresa empresa = null;
        ResultSet resultSet = SingletonDB.getConexao().consultar(sql);

        try {
            if(resultSet.next()) {
                empresa = new Empresa(resultSet.getInt("empresa_id"),
                        resultSet.getString("empresa_razao"),
                        resultSet.getString("empresa_fantasia"),
                        resultSet.getString("emppresa_cnpj"),
                        resultSet.getString("empresa_cep"),
                        resultSet.getString("empresa_rua"),
                        resultSet.getString("empresa_numero"),
                        resultSet.getString("empresa_bairro"),
                        resultSet.getString("empresa_cidade"),
                        resultSet.getString("empresa_uf"),
                        resultSet.getString("empresa_fone"),
                        resultSet.getString("empresa_email"),
                        resultSet.getDouble("empresa_valor_embalagem"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empresa;
    }

    @Override
    public List<Empresa> get(String filtro) {
        List<Empresa> empresas = new ArrayList<>();
        String sql = "SELECT * FROM empresa";

        if(!filtro.isEmpty())
            sql = sql + " WHERE " + filtro;

        ResultSet resultSet = SingletonDB.getConexao().consultar(sql);
        try{
            while (resultSet.next()) {
                Empresa empresa = new Empresa(resultSet.getInt("empresa_id"),
                        resultSet.getString("empresa_razao"),
                        resultSet.getString("empresa_fantasia"),
                        resultSet.getString("emppresa_cnpj"),
                        resultSet.getString("empresa_cep"),
                        resultSet.getString("empresa_rua"),
                        resultSet.getString("empresa_numero"),
                        resultSet.getString("empresa_bairro"),
                        resultSet.getString("empresa_cidade"),
                        resultSet.getString("empresa_uf"),
                        resultSet.getString("empresa_fone"),
                        resultSet.getString("empresa_email"),
                        resultSet.getDouble("empresa_valor_embalagem"));
                empresas.add(empresa);
            }
        } catch (SQLException e) {e.printStackTrace();}

        return empresas;
    }
}
