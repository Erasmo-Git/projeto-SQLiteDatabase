package com.example.myapplication.database.contract;

import com.example.myapplication.database.entity.CategoriaEntity;
import com.example.myapplication.database.entity.ProdutoEntity;

public final class ProdutoContract { // para tudo o que precisa ser feito para manipular as tabelas

    private ProdutoContract (){}

    public static final String criarTabela(){
        return "CREATE TABLE " + ProdutoEntity.TABLE_NAME + "(" +
                ProdutoEntity._ID + " INTEGER PRIMARY KEY," +
                ProdutoEntity.COLUMN_NAME_NOME + " TEXT," +
                ProdutoEntity.COLUMN_NAME_VALOR + " REAL," +
                ProdutoEntity.COLUMN_NAME_ID_CATEGORIA + " INTEGER," + "FOREIGN KEY (" +
                ProdutoEntity.COLUMN_NAME_ID_CATEGORIA + ") REFERENCES " +
                CategoriaEntity.TABLE_NAME + "(" + CategoriaEntity._ID + "))";
    }

    public static final String removerTabela (){
        return " DROP TABLE IF EXISTS " + ProdutoEntity.TABLE_NAME;
    }
}
