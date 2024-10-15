package com.faiskaburgers.faiskaburger.database.util;

public class SingletonDB {
    private static Conexao conexao=null;

    private SingletonDB() {
    }

    public static boolean conectar()
    {
        conexao=new Conexao();
        return conexao.conectar("jdbc:postgresql://localhost:5432/","FaiskaBurgerDB","postgres","1234");
    }
    public static Conexao getConexao() {
        return conexao;
    }
}
