package com.example.android_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityExibirNota extends AppCompatActivity {

    private NotaController controller;
    private EditText edTitulo, edTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = new NotaController(getApplicationContext());

        edTitulo = findViewById(R.id.txtTitulo);
        edTexto = findViewById(R.id.txtTexto);
        setContentView(R.layout.activity_exibir_nota);
    }

    public void salvarNota(View v) {
        Nota n = controller.cadastrarNovaNota(new Nota(edTitulo.getText().toString(), edTexto.getText().toString()));
        Toast.makeText(this, Integer.toString(n.getId()), Toast.LENGTH_SHORT).show();
    }
}