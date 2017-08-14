package com.example.jamile.cadernocientifico14;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jamile on 11/08/2017.
 */

public class CadernoCientificoBD extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME ="teste"; //"CadernoCientifico1.3";

    private static final String TABELA_PAGINA = "pagina";
    private static final String ID_PAGINA = "id";
    private static final String CAMINHOBACKGROUND = "caminhoBackground";
    private static final String[] COLUNAS_PAGINA = {ID_PAGINA, CAMINHOBACKGROUND};

    private static final String TABELA_PONTO = "ponto";
    private static final String ID_PONTO = "id";
    private static final String POSICAOX = "posicaoX";
    private static final String POSICAOY = "posicaoY";
    private static final String TIPO = "tipo";
    private static final String ID_PAGINA2 = "numPagina";
    private static final String[] COLUNAS_PONTO = {ID_PONTO, POSICAOX, POSICAOY, TIPO, ID_PAGINA2};

    public CadernoCientificoBD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_PAGINA = "CREATE TABLE pagina ("+
                "numPagina INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "caminhoBackground TEXT)";
        db.execSQL(CREATE_TABLE_PAGINA);

        String CREATE_TABLE_PONTO = "CREATE TABLE ponto ("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "posicaoX REAL,"+
                "posicaoY REAL,"+
                "tipo INTEGER,"+
                "caminhoLink TEXT,"+
                "numPagina INTEGER)";
        //FOREIGN KEY (PersonID) REFERENCES Persons(PersonID) //ON DELETE RESTRICT ON UPDATE CASCADE
        db.execSQL(CREATE_TABLE_PONTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS pagina");
        this.onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS ponto");
        this.onCreate(db);
    }
}
