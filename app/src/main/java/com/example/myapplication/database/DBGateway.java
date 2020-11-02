package com.example.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBGateway { // ponto de acesso atraves de do getInstance

    private static DBGateway dbGateway;
    private SQLiteDatabase db;

    public static DBGateway getInstance(Context context){
        if (dbGateway == null){
            dbGateway = new DBGateway(context);
        }
        return dbGateway;
    }

    private DBGateway (Context context) {
        DatabaseDbHelper dbHelper = new DatabaseDbHelper(context);
        db = dbHelper.getWritableDatabase(); // abre conexao para ler e escrever dados
    }

    public SQLiteDatabase getDatabase(){
        return db;
    }
}
