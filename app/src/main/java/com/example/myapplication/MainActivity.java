package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.database.ProdutoDAO;
import com.example.myapplication.database.entity.ProdutoEntity;
import com.example.myapplication.modelo.Categoria;
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
        definirOnClikOpcoes();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ProdutoDAO produtoDAO = new ProdutoDAO(getBaseContext());
        adapterProdutos = new ArrayAdapter<Produto>(MainActivity.this, android.R.layout.simple_list_item_1, produtoDAO.listar());
        ListViewProdutos.setAdapter(adapterProdutos);
    }

    public void definirOnClikOpcoes(){
        ProdutoDAO pDAO = new ProdutoDAO(getBaseContext());
        ListViewProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder opcoes = new AlertDialog.Builder(MainActivity.this);
                opcoes.setTitle("exclua o produto selecionado ou cancele");
                opcoes.setNeutralButton("cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                opcoes.setPositiveButton("editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Produto produtoClicado = adapterProdutos.getItem(position);
                        Intent intent = new Intent(MainActivity.this, CadastroProdutoActivity.class);
                        intent.putExtra("produtoEdicao", produtoClicado);
                        startActivity(intent);
                        dialog.cancel();
                    }
                });

                opcoes.setNegativeButton("excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Produto produtoClicadoExcluir = (Produto) adapterProdutos.getItem(position);
                        boolean excluiu = pDAO.excluir(produtoClicadoExcluir.getId());

                        if (excluiu){
                            adapterProdutos.remove(produtoClicadoExcluir);
                            Toast.makeText(MainActivity.this, "produto excluido", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "erro ao excluir produto", Toast.LENGTH_LONG).show();
                        }
                        dialog.cancel();
                    }
                });
                opcoes.create().show();
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