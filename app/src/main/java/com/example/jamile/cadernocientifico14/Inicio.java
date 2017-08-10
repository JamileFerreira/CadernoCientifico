package com.example.jamile.cadernocientifico14;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Inicio extends AppCompatActivity {
    private ImageView imagem;
    private final int GALERIA_IMAGENS = 1;
    private final int PERMISSAO_REQUEST = 2;
    Bitmap thumbnail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        //Abrindo a galeria
        //imagem = (ImageView) findViewById(R.id.ivImagem);
        imagem = (ImageView) findViewById(R.id.img);
        Button galeria = (Button) findViewById(R.id.btimg);
        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Inicio.this, AbrirGaleria.class);
                startActivity(intent);


            }
        });

        Button camera = (Button) findViewById(R.id.btcam);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Inicio.this, AbrirCamera.class);
                startActivity(intent);
            }
        });
    }
}
