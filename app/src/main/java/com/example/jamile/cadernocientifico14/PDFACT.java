package com.example.jamile.cadernocientifico14;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;

public class PDFACT extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfact);

        Intent intent2 = getIntent();
        Bundle bundle= intent2.getExtras();
        String picturePath = bundle.getString("CaminhoLink");

        Log.i("Caminho_aperteiM", String.valueOf(picturePath));

        File file = new File(picturePath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }
}
