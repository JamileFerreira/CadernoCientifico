package com.example.jamile.cadernocientifico14;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Jamile on 11/08/2017.
 */

public class PontoBD {
    private static final String TABELA_PONTO = "ponto";
    private static final String ID_PONTO = "id";
    private static final String POSICAOX = "posicaoX";
    private static final String POSICAOY = "posicaoY";
    private static final String TIPO = "tipo";
    private static final String NUMPAGINA = "numPagina";
    private static final String CAMINHOLINK = "caminhoLink";

    private static final String[] COLUNAS_PONTO = {ID_PONTO, POSICAOX, POSICAOY, TIPO, CAMINHOLINK, NUMPAGINA};


    private SQLiteDatabase bd;

    public PontoBD(Context context){
        CadernoCientificoBD cadernoCBD= new CadernoCientificoBD(context);
        bd=cadernoCBD.getWritableDatabase();
    }


    public void addPonto(Ponto ponto) {
        Log.i("TIPO PONTO::::::::::", String.valueOf(ponto.getTipo()));
        Log.i("TIPO2 2 2 2 2::::::::::", ponto.getCaminhoLink());
        Log.i("TIPO2 2 2 2 2::::::::::", String.valueOf(ponto.getNumPagina()));
        // }
        ContentValues values = new ContentValues();
        values.put(POSICAOX, ponto.getPossicaoX());
        values.put(POSICAOY,ponto.getPossicaoY());
        values.put(TIPO, ponto.getTipo());
        values.put(CAMINHOLINK, ponto.getCaminhoLink());
        values.put(NUMPAGINA, ponto.getNumPagina());
        //values.put("numPagina", 2);
        bd.insert(TABELA_PONTO, null, values);
        bd.close();
    }

    public int updateLivro(Ponto ponto) {

        ContentValues values = new ContentValues();
        values.put(POSICAOX, ponto.getPossicaoX());
        values.put(POSICAOY,ponto.getPossicaoY());
        //values.put(TIPO, ponto.getTipo());
        //values.put(CAMINHOLINK, ponto.getCaminhoLink());
        //values.put(NUMPAGINA, ponto.getNumPagina());
        int i = bd.update(TABELA_PONTO, //tabela
                values, // valores
                ID_PONTO+" = ?", // colunas para comparar
                new String[] { String.valueOf(ponto.getId()) }); //parâmetros
        bd.close();
        return i; // número de linhas modificadas
    }

    public ArrayList<Ponto> getAllPontos() {
        ArrayList<Ponto> listaPonto = new ArrayList<Ponto>();
        String query = "SELECT * FROM " + TABELA_PONTO;

        Cursor cursor = bd.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Ponto ponto = cursorToLivro(cursor);
                listaPonto.add(ponto);
            } while (cursor.moveToNext());
        }
        return listaPonto;
    }

    public ArrayList<Ponto> getAllPontosIdPagina(int numPagina) {
        ArrayList<Ponto> listaPonto = new ArrayList<Ponto>();
        String query = "SELECT * FROM " + TABELA_PONTO +" where numPagina = "+ numPagina+";";

        Cursor cursor = bd.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Ponto ponto = cursorToLivro(cursor);
                listaPonto.add(ponto);
            } while (cursor.moveToNext());
        }
        return listaPonto;
    }

    public int deletePonto(Ponto ponto) {
        int i = bd.delete(TABELA_PONTO, //tabela
                ID_PONTO+" = ?", // colunas para comparar
                new String[] { String.valueOf(ponto.getId()) });
        bd.close();
        return i; // número de linhas excluídas
    }

    private Ponto cursorToLivro(Cursor cursor) {
        Ponto ponto = new Ponto();
        ponto.setId(Integer.parseInt(cursor.getString(0)));
        ponto.setPossicaoX(Float.parseFloat(cursor.getString(1)));
        ponto.setPossicaoY(Float.parseFloat(cursor.getString(2)));
        ponto.setTipo(Integer.parseInt(cursor.getString(3)));
        ponto.setCaminhoLink(cursor.getString(4));
        ponto.setNumPagina(Integer.parseInt(cursor.getString(5)));

        Log.i("TIPO2 2 2 2 2::::::::::", String.valueOf(ponto.getId()));
        Log.i("TIPO2 2 2 2 2::::::::::", String.valueOf(ponto.getPossicaoX()));
        Log.i("TIPO2 2 2 2 2::::::::::", String.valueOf(ponto.getPossicaoY()));
        Log.i("TIPO2 2 2 2 2::::::::::", String.valueOf(ponto.getTipo()));
        Log.i("TIPO2 2 2 2 2::::::::::", String.valueOf(ponto.getCaminhoLink()));
        Log.i("TIPO2 2 2 2 2::::::::::", String.valueOf(ponto.getNumPagina()));
        return ponto;
    }
}