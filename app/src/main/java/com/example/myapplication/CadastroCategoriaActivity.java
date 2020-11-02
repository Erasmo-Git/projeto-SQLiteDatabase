package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.database.CategoriaDAO;
import com.example.myapplication.modelo.Categoria;

public class CadastroCategoriaActivity extends AppCompatActivity {

    private int id  = 0;
    private EditText editTextDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrocategoria);
        setTitle("cadastro de Categoria");
        editTextDescricao = findViewById(R.id.editTex_descricao_categoria);
        carregarCategoria();
    }

    public void carregarCategoria(){
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().get("categoriaEdicao") != null){
            Categoria categoria = (Categoria) intent.getExtras().get("categoriaEdicao");
            editTextDescricao.setText(categoria.getDescricao());
            id = categoria.getId();
        }
    }

    public void onclickVoltar(View v){ finish(); }

    public void onclickSalvar(View v){
        String descricao = editTextDescricao.getText().toString();
        Categoria categoria = new Categoria(id, descricao);
        CategoriaDAO categoriaDAO = new CategoriaDAO(getBaseContext());
        boolean salvou = categoriaDAO.salvar(categoria);
        if (salvou){
            finish();
        } else {
            Toast.makeText(CadastroCategoriaActivity.this, "Erro ao Salvar", Toast.LENGTH_LONG).show();
        }
    }
}