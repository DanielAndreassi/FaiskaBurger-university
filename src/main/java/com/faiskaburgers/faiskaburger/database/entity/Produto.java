package com.faiskaburgers.faiskaburger.database.entity;

public class Produto {
    private int idProduto;
    private String nomeProduto;
    private String descricaoProduto;
    private double valorProduto;
    private Categoria categoria;

    public Produto(int idProduto, String nomeProduto, String descricaoProduto, double valorProduto, Categoria categoria) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.valorProduto = valorProduto;
        this.categoria = categoria; //FK do banco cuidados para manipular
    }

    public Produto(String nomeProduto, String descricaoProduto, double valorProduto, Categoria categoria) {
        this(0, nomeProduto, descricaoProduto, valorProduto, categoria);
    }

    public Produto() {
        this(0,"","",0,null);
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(double valorProduto) {
        this.valorProduto = valorProduto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return  nomeProduto;
    }
}
