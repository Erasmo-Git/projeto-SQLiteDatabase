package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.database.ProdutoDAO;
import com.example.myapplication.database.entity.ProdutoEntity;
import com.example.myapplication.modelo.Produto;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int id = 0;
    private ListView ListViewProdutos;
    private ArrayAdapter<Produto> adapterProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("PRODUTOS");
        ListViewProdutos = findViewById(R.id.ListView_produtos);
        definirOnClickListenerListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ProdutoDAO produtoDAO = new ProdutoDAO(getBaseContext());
        adapterProdutos = new ArrayAdapter<Produto>(MainActivity.this, android.R.layout.simple_list_item_1, produtoDAO.listar());
        ListViewProdutos.setAdapter(adapterProdutos);
    }

    public void definirOnClickListenerListView (){
        ListViewProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto produtoClicado = adapterProdutos.getItem(position);
                Intent intent = new Intent(MainActivity.this, CadastroProdutoActivity.class);
                intent.putExtra("produtoEdicao", produtoClicado);
                startActivity(intent);
            }
        });
    }

    public void onClickNovoProduto(View v){
        Intent intent = new Intent(MainActivity.this, CadastroProdutoActivity.class);
        startActivity(intent); // abre nova tela esperando um resultado quando ela fechar- REQUEST_CODE_NOVO_PRODUTO- ajuda a saber se o resultado foi da acoa desta startActivityForResult
    }

    public void onClickCategorias(View v){
        Intent intent = new Intent(MainActivity.this, ListarCategoriasActivity.class);
        startActivity(intent);
        finish();
    }
}