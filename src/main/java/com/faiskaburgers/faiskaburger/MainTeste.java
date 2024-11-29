package com.faiskaburgers.faiskaburger;

import com.faiskaburgers.faiskaburger.database.dal.PedidoDAL;
import com.faiskaburgers.faiskaburger.database.dal.ProdutoDAL;
import com.faiskaburgers.faiskaburger.database.dal.TipoPagamentoDAL;
import com.faiskaburgers.faiskaburger.database.entity.Pedido;
import com.faiskaburgers.faiskaburger.database.entity.Produto;
import com.faiskaburgers.faiskaburger.database.entity.TipoPagamento;
import com.faiskaburgers.faiskaburger.database.util.SingletonDB;

import java.util.Date;
import java.util.List;

public class MainTeste {
    public static void main(String[] args) {
        if(!SingletonDB.conectar()) System.out.println("Erro: "+SingletonDB.getConexao().getMensagemErro());
        PedidoDAL pedidoDAL;
        ProdutoDAL produtoDAL = new ProdutoDAL();

        Produto produto = new Produto();
        produto = produtoDAL.get(1);
        pedidoDAL = new PedidoDAL();
        Date data = new Date();
        Pedido pedido = new Pedido();
        TipoPagamentoDAL tipoPagamentoDAL = new TipoPagamentoDAL();
        tipoPagamentoDAL.gravar(new TipoPagamento("PIX"));
        tipoPagamentoDAL.get("PIX");
        //pedidoDAL.gravar(data.toString(),"Daniel Andreassi","18996028213",12.50,"S",);
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
