package com.example.jamile.cadernocientifico14;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AdicionarLink extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_link);
        ExibeDialog();
        //passar a imagem da outra activi pra simular mesma tela
    }

    private void ExibeDialog() {
        final Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.customdialog);

        //define o título do Dialog
        dialog.setTitle("Insira o Link:");

        //instancia os objetos que estão no layout customdialog.xml
        final Button confirmar = (Button) dialog.findViewById(R.id.btn_Confirmar);
        final Button cancelar = (Button) dialog.findViewById(R.id.btn_Cancelar);
        final EditText editText = (EditText) dialog.findViewById(R.id.etValor);
        final TextView tvMens = (TextView) dialog.findViewById(R.id.tvMens);
//String d= (String) tvMens.getText();

        // tvMens.setText("Nome");

        confirmar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String caminhoLink = editText.getText().toString();
                //CaminhoLink = cam;
                //Log.i("LINK666", CaminhoLink);
                //mover.criarPonto(LINK);
                //caminhoPDF = pdfDoc.getPath();
                dialog.dismiss();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("resultado", caminhoLink);
                setResult(RESULT_OK, returnIntent);
                finish();


                // passar pra outra activit o texto

                //finaliza o dialog
                //dialog.dismiss();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //finaliza o dialog
                dialog.dismiss();
                finish();
            }
        });

        //exibe na tela o dialog
        dialog.show();

    }

}
