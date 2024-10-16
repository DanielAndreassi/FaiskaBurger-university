package com.faiskaburgers.faiskaburger.database.entity;

public class Empresa {
    private int empresa_id;
    private String razao_social;
    private String cnpj;
    private String cep;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String UF;
    private String telefone;
    private String email;
    private double valor_embalagem;

    public Empresa(int empresa_id, String razao_social, String cnpj, String cep, String rua, String numero, String bairro, String cidade, String UF, String telefone, String email, double valor_embalagem) {
        this.empresa_id = empresa_id;
        this.razao_social = razao_social;
        this.cnpj = cnpj;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.UF = UF;
        this.telefone = telefone;
        this.email = email;
        this.valor_embalagem = valor_embalagem;
    }

    public Empresa(String razao_social, String cnpj, String cep, String rua, String numero, String bairro, String cidade, String UF, String telefone, String email, double valor_embalagem) {
        this(0,razao_social,cnpj,cep,rua,numero,bairro,cidade,UF,telefone,email,valor_embalagem);
    }

    public Empresa() {
        this(0,"","","","","","","","","","",0.0);
    }

    public int getEmpresa_id() {
        return empresa_id;
    }

    public void setEmpresa_id(int empresa_id) {
        this.empresa_id = empresa_id;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getValor_embalagem() {
        return valor_embalagem;
    }

    public void setValor_embalagem(double valor_embalagem) {
        this.valor_embalagem = valor_embalagem;
    }

    @Override
    public String toString() {
        return razao_social;
    }
}
