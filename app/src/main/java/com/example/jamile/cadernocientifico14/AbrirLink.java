package com.example.jamile.cadernocientifico14;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class AbrirLink extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abrir_link);
        Intent intent2 = getIntent();
        Bundle bundle= intent2.getExtras();
        String url = bundle.getString("CaminhoLink");
        Log.i("LINK6668", url);
        browseTo(url);
        finish();
    }
    public void browseTo(String url){
      //  String url="globo.com";
        if (!url.startsWith("http://") && !url.startsWith("https://")){
            url = "http://" + url;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
