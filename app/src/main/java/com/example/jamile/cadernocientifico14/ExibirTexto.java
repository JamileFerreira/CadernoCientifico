package com.example.jamile.cadernocientifico14;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ExibirTexto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_texto);
        final TextView texto = (TextView)findViewById(R.id.texto);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String caminhoTexto = bundle.getString("texto");
        texto.setText(caminhoTexto);
        ///Log.i("TESTE 44444::::::::::", caminhoImagem);


    }
}
