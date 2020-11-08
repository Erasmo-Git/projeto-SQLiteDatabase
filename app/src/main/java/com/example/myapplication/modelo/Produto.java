package com.example.myapplication.modelo;

import android.content.Context;

import com.example.myapplication.database.ProdutoDAO;

import java.io.Serializable;

public class Produto implements Serializable { // objeto do tipo serealizable

    private String nome;
    private float valor;
    private int id;
    private Categoria categoria;

    ProdutoDAO produtoDAO;

    public Produto(int id, String nome, float valor, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.categoria = categoria;
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

    public void setId(int id) { this.id = id; }

    public Categoria getCategoria() { return categoria; }

    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    @Override
    public String toString() {
        return id + "-" + nome;
    }

//    public boolean exclucaoProduto (long idProdutoExcluido){
//        return this.produtoDAO.excluir(idProdutoExcluido);
//    }
}
