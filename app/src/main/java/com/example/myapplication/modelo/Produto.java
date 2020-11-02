package com.example.myapplication.modelo;

import java.io.Serializable;

public class Produto implements Serializable { // objeto do tipo objeto
    private String nome;
    private float valor;
    private int id;

    public Produto(String nome, float valor, int id) {
        this.nome = nome;
        this.valor = valor;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + "-" + nome;
    }
}
