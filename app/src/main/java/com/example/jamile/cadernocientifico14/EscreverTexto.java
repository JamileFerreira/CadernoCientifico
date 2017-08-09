package com.example.jamile.cadernocientifico14;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EscreverTexto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escrever_texto);
        final EditText editText = (EditText)findViewById(R.id.editText);

        Button enviar = (Button) findViewById(R.id.btEnviar);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String texto = editText.getText().toString();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("resultado", texto);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}
