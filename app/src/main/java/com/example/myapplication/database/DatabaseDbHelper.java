package com.example.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.database.contract.CategoriaContract;
import com.example.myapplication.database.contract.ProdutoContract;

public class DatabaseDbHelper extends SQLiteOpenHelper { // para iniciar o banco de dados, cria-lo se nao existir e fazer update


    public static final String DATABASE_NAME = "db.produto_v2";
    public static final int DATABASE_VERSION = 4;

    public DatabaseDbHelper(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CategoriaContract.criarTabela());
        db.execSQL(ProdutoContract.criarTabela());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ProdutoContract.removerTabela());
        db.execSQL(CategoriaContract.removerTabela());
        db.execSQL(CategoriaContract.criarTabela());
        db.execSQL(ProdutoContract.criarTabela());
    }
}
