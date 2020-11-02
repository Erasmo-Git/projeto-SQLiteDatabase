package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.database.ProdutoDAO;
import com.example.myapplication.modelo.Produto;

public class CadastroProdutoActivity extends AppCompatActivity {

    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);
        setTitle("Cadastro de Produto");

        carregarProduto();
    }

    private void carregarProduto(){
        Intent intent = getIntent();
        if(intent != null && intent.getExtras() != null && intent.getExtras().get("produtoEdicao") != null){
            Produto produto = (Produto) intent.getExtras().get("produtoEdicao");
            EditText editTextNome = findViewById(R.id.editText_Nome);
            EditText editTextValor = findViewById(R.id.editText_Valor);

            editTextNome.setText(produto.getNome());
            editTextValor.setText(String.valueOf(produto.getValor()));
            id = produto.getId();
        }
    }

    public void onClickVoltar(View v){
        finish();
    }

    public void onClickSalvar(View v){
        EditText editTextNome = findViewById(R.id.editText_Nome);
        EditText editTextValor = findViewById(R.id.editText_Valor);

        String nome = editTextNome.getText().toString();
        float valor = Float.parseFloat(editTextValor.getText().toString());

        Produto produto = new Produto(nome, valor, id);
        ProdutoDAO produtoDAO = new ProdutoDAO(getBaseContext());
        boolean salvou = produtoDAO.salvar(produto);
        if (salvou){
            finish();
        }else {
            Toast.makeText(CadastroProdutoActivity.this, "erro ao salvar", Toast.LENGTH_LONG).show();
        }
    }
}