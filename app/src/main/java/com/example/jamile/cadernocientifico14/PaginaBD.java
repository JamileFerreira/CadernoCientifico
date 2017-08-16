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

public class PaginaBD {

    private static final String TABELA_PAGINA = "pagina";
    private static final String ID_PAGINA = "id";
    private static final String CAMINHOBACKGROUND = "caminhoBackground";
    private static final String NUMPAGINA="numPagina";
    private static final String[] COLUNAS_PAGINA = {NUMPAGINA, CAMINHOBACKGROUND};

    private SQLiteDatabase bd;

    public PaginaBD(Context context){
        CadernoCientificoBD cadernoCBD= new CadernoCientificoBD(context);
        bd=cadernoCBD.getWritableDatabase();
    }
    public void addPagina(Pagina pagina) {
        //SQLiteDatabase db = this.getWritableDatabase();
       // Log.i("TIPO2222::::::::::", String.valueOf(ponto.getTipo()));
///        Log.i("TIPO PAGINA::::::::::", pagina.getCaminhoBackground());
        ContentValues values = new ContentValues();
        values.put(CAMINHOBACKGROUND, pagina.getCaminhoBackground());
        bd.insert(TABELA_PAGINA, null, values);
        bd.close();
        //PontoBD pontoBD=new PontoBD(this);
        //addPonto(pagina.getPontos());
    }

    public Pagina getPaginna(int id) {
        //SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = bd.query(TABELA_PAGINA, COLUNAS_PAGINA,  " numPagina = ?",
                new String[] { String.valueOf(id) },
                null, null, null);
        if (cursor == null) {
            return null;
        } else {
            cursor.moveToFirst();
            Pagina pagina = cursorToPagina(cursor);
            return pagina;
        }
    }


   // SELECT Max(campo) FROM tabela;



    public Pagina getPaginnaIdMax() {
        Pagina pagina = new Pagina();
        String query = "select max(numPagina) from " + TABELA_PAGINA;

        Cursor cursor = bd.rawQuery(query, null);

        if(cursor!=null){
            Log.i("TIPO PAGINA TODAS curso::::::::::", "111");
        }
        else {
            Log.i("TIPO PAGINA TODAS11333: curso:::::::::", "222");
        }
        if (cursor.moveToFirst()) {

//            Log.i("TIPO PAGINA TODAS1111cursoaaaa::::::::::", "DEUS!");
//            do {

                 pagina = cursorToPagina2(cursor);
               // listaPagina.add(pagina);
               // Log.i("TIPO PAGINA TODAS11111curso OOOOPAACACAPIMMM::::::::::", listaPagina.get(0).getCaminhoBackground());
            //} while (cursor.moveToNext());
        }
        // Log.i("TIPO PAGINA TODAS::::::::::", listaPagina.get(0).getCaminhoBackground());
        return pagina;
    }


    public int updateLivro(Pagina pagina) {

        ContentValues values = new ContentValues();
        values.put(CAMINHOBACKGROUND, pagina.getCaminhoBackground());
        int i = bd.update(TABELA_PAGINA, //tabela
                values, // valores
                NUMPAGINA+" = ?", // colunas para comparar
                new String[] { String.valueOf(pagina.getNumPagina()) }); //parâmetros
        bd.close();
        return i; // número de linhas modificadas
    }


    public int deletePagina(Pagina pagina) {
        int i = bd.delete(TABELA_PAGINA, //tabela
                NUMPAGINA+" = ?", // colunas para comparar
                new String[] { String.valueOf(pagina.getNumPagina()) });
        bd.close();
        return i; // número de linhas excluídas
    }


    public ArrayList<Pagina> getAllPaginas() {
        ArrayList<Pagina> listaPagina = new ArrayList<Pagina>();
        String query = "SELECT * FROM " + TABELA_PAGINA;

        Cursor cursor = bd.rawQuery(query, null);

        if(cursor!=null){
            Log.i("TIPO PAGINA TODAS curso::::::::::", "111");
        }
        else {
            Log.i("TIPO PAGINA TODAS11333: curso:::::::::", "222");
        }
        if (cursor.moveToFirst()) {

            Log.i("TIPO PAGINA TODAS1111cursoaaaa::::::::::", "DEUS!");
            do {
                Pagina pagina = cursorToPagina(cursor);
                listaPagina.add(pagina);
                Log.i("TIPO PAGINA TODAS11111curso OOOOPAACACAPIMMM::::::::::", listaPagina.get(0).getCaminhoBackground());
            } while (cursor.moveToNext());
        }
       // Log.i("TIPO PAGINA TODAS::::::::::", listaPagina.get(0).getCaminhoBackground());
        return listaPagina;
    }


    private Pagina cursorToPagina(Cursor cursor) {
        Pagina pagina = new Pagina();
        pagina.setNumPagina(Integer.parseInt(cursor.getString(0)));
        pagina.setCaminhoBackground(cursor.getString(1));
        return pagina;
    }
    private Pagina cursorToPagina2(Cursor cursor) {
        Pagina pagina = new Pagina();
        pagina.setNumPagina(Integer.parseInt(cursor.getString(0)));
        //pagina.setCaminhoBackground(cursor.getString(1));
        return pagina;
    }
}


