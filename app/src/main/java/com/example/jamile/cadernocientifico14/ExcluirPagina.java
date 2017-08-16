package com.example.jamile.cadernocientifico14;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ExcluirPagina extends AppCompatActivity {
    private int numPagina;
    private PontoBD pontoBD;
    private PaginaBD paginaBD;
    ArrayList<Ponto> pontosExcluir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excluir_pagina);
        Intent intent = getIntent();
        numPagina = intent.getIntExtra("numPagina", 0);
        Log.i("numPagina 2::::::::::", String.valueOf(numPagina));
        ExibeDialog();
        //finish();
    }

    private void ExibeDialog() {
        final Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.customdialogexcluir);

        //define o título do Dialog
        dialog.setTitle("Tem certeza?");

        //instancia os objetos que estão no layout customdialog.xml
        final Button confirmar = (Button) dialog.findViewById(R.id.btn_Confirmar);
        final Button cancelar = (Button) dialog.findViewById(R.id.btn_Cancelar);
       // final EditText editText = (EditText) dialog.findViewById(R.id.etValor);
       // final TextView tvMens = (TextView) dialog.findViewById(R.id.tvMens);
       //String d= (String) tvMens.getText();

        // tvMens.setText("Nome");

        confirmar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
                pontoBD=new PontoBD(ExcluirPagina.this);
                pontosExcluir=pontoBD.getAllPontosIdPagina(numPagina);

                for(int i=0;i< pontosExcluir.size();i++){
                    pontoBD=new PontoBD(ExcluirPagina.this);
                    pontoBD.deletePonto(pontosExcluir.get(i));
                }
                paginaBD=new PaginaBD(ExcluirPagina.this);
                Pagina pagina=new Pagina();
                pagina.setNumPagina(numPagina);
                paginaBD.deletePagina(pagina);

                Intent intent = new Intent(ExcluirPagina.this, CadernoCientifico.class);
                //intent.putExtra("numPagina", numPagina);
                startActivity(intent);
               // finish();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });
        //exibe na tela o dialog
        dialog.show();
    }
}
