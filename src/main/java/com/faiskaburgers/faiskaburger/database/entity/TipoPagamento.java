package com.faiskaburgers.faiskaburger.database.entity;

public class TipoPagamento {
    private int idTipoPagamento;
    private String tipoPagamento;

    public TipoPagamento(String tipoPagamento, int idTipoPagamento) {
        this.tipoPagamento = tipoPagamento;
        this.idTipoPagamento = idTipoPagamento;
    }

    public TipoPagamento(String tipoPagamento) {
        this(tipoPagamento,0);
    }

    public TipoPagamento() {
        this("",0);
    }

    public int getIdTipoPagamento() {
        return idTipoPagamento;
    }

    public void setIdTipoPagamento(int idTipoPagamento) {
        this.idTipoPagamento = idTipoPagamento;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    @Override
    public String toString() {
        return tipoPagamento;
    }
}
