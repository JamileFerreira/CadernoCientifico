package com.example.jamile.cadernocientifico14;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ModificaPagina extends AppCompatActivity {
    private int numPagina;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        // setContentView(R.layout.activity_modifica_pagina);
        Intent intent = getIntent();
        numPagina = intent.getIntExtra("numPagina", 0);
        Log.i("numPagina 1 ::::::::::", String.valueOf(numPagina));
        exibeDialog();
    }

    private void exibeDialog() {
        final Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.customdialogve);

        //define o título do Dialog
        dialog.setTitle("O que deseja fazer?");

        //instancia os objetos que estão no layout customdialog.xml
        final Button visualizar = (Button) dialog.findViewById(R.id.btn_visualizar);
        final Button excluir = (Button) dialog.findViewById(R.id.btn_excluir);
        //final EditText editText = (EditText) dialog.findViewById(R.id.etValor);
       // final TextView tvMens = (TextView) dialog.findViewById(R.id.tvMens);
//String d= (String) tvMens.getText();

        // tvMens.setText("Nome");

        visualizar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(ModificaPagina.this, AtualizaPagina.class);
                intent.putExtra("numPagina", numPagina);
                startActivity(intent);
                finish();
            }
        });

        excluir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //finaliza o dialog
                dialog.dismiss();
                Intent intent = new Intent(ModificaPagina.this, ExcluirPagina.class);
                intent.putExtra("numPagina", numPagina);
                startActivity(intent);
                finish();
            }
        });

        //exibe na tela o dialog
        dialog.show();

    }
}
