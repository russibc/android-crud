package com.example.android_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Intent intentExibirNota;
    private NotaController controller;
    private List<String> dataset;
    private List<Nota> arrayNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
    }

    public void novaNota(View v) {
        intentExibirNota = new Intent(this, ActivityExibirNota.class);
        intentExibirNota.putExtra("id nota", 0);
        startActivity(intentExibirNota);
    }

    @Override
    protected void onStart() {
        super.onStart();
        controller = new NotaController(this);
        arrayNotas = controller.listaNotas();
        carregaLista();
    }

    private void carregaLista() {
        dataset = controller.listaTitulosNotas();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                dataset
        );
        listView.setAdapter(adapter);
    }

    public ListView.OnItemClickListener clickItemListView = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long ide) {
            Intent intent = new Intent(MainActivity.this, ActivityEditarNota.class);
            String valor = String.valueOf(arrayNotas.get(position).getId());
            intent.putExtra("id", valor);
            intent.putExtra("titulo", arrayNotas.get(position).getTitulo());
            intent.putExtra("texto", arrayNotas.get(position).getTexto());
            startActivity(intent);
        }
    };
}