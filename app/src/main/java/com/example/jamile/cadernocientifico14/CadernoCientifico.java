package com.example.jamile.cadernocientifico14;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CadernoCientifico extends AppCompatActivity {
    private PaginaBD paginaBD;
    ArrayList<Pagina> listaPagina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caderno_cientifico);
        paginaBD = new PaginaBD(this);
        Button AdicionarPagina = (Button) findViewById(R.id.addPagina);
        AdicionarPagina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chamar incnio
                Intent intent = new Intent(CadernoCientifico.this, Inicio.class);
                startActivity(intent);
                Toast.makeText(getBaseContext(), "Livro alterado com sucesso.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ListView lista = (ListView) findViewById(R.id.paginas);
        listaPagina = paginaBD.getAllPaginas();
        PaginaAdapter adapter = new PaginaAdapter(this, listaPagina);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(CadernoCientifico.this, AtualizaPagina.class);
                intent.putExtra("numPagina", listaPagina.get(position).getNumPagina());
                startActivity(intent);
            }
        });
    }
}
