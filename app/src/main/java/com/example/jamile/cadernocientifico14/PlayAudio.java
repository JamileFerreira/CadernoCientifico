package com.example.jamile.cadernocientifico14;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;

public class PlayAudio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_audio);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String caminhoImagem = bundle.getString("caminhoImagem");
        Log.i("TESTE 44444::::::::::", caminhoImagem);
        openFullImage(caminhoImagem);

    }

    private void openFullImage( String caminhoImagem )
    {

        File file = new File(caminhoImagem);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "audio/*");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();


//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.setDataAndType(Uri.parse(caminhoImagem), "image/*");
//        startActivity(intent);
    }
}
