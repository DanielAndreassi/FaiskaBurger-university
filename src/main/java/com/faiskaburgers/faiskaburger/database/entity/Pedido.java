package com.faiskaburgers.faiskaburger.database.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    public record Item(Produto produto,int quantidade, double valor){}
    private int idPedido;
    private LocalDate dataPedido;
    private String nomeCliente;
    private String foneCliente;
    private Double totalPedido;
    private char viagem; //S ou N
    private TipoPagamento tipoPagamento;
    private List<Item> itens;

    public Pedido(int idPedido, LocalDate dataPedido, String nomeCliente, String foneCliente, Double totalPedido, char viagem, TipoPagamento tipoPagamento) {
        this.idPedido = idPedido;
        this.dataPedido = dataPedido;
        this.nomeCliente = nomeCliente;
        this.foneCliente = foneCliente;
        this.totalPedido = totalPedido;
        this.viagem = viagem;
        this.tipoPagamento = tipoPagamento;
        itens = new ArrayList<Item>();
    }

    public Pedido(LocalDate dataPedido, String nomeCliente, String foneCliente, Double totalPedido, char viagem, TipoPagamento tipoPagamento) {
        this(0,dataPedido,nomeCliente, foneCliente, totalPedido, viagem, tipoPagamento);
    }

    public Pedido() {
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getFoneCliente() {
        return foneCliente;
    }

    public void setFoneCliente(String foneCliente) {
        this.foneCliente = foneCliente;
    }

    public Double getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(Double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public char getViagem() {
        return viagem;
    }

    public void setViagem(char viagem) {
        this.viagem = viagem;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public boolean addItem(Produto produto, int quantidade) {
        return itens.add(new Item(produto,quantidade,produto.getValorProduto()));
    }

    public List<Item> getItens() {
        return itens;
    }


}
