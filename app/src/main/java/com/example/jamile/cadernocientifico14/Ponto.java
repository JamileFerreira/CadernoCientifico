package com.example.jamile.cadernocientifico14;

import android.graphics.Paint;

/**
 * Created by Jamile on 19/07/2017.
 */

public class Ponto {

    private String caminhoLink;
    private float possicaoX;
    private float possicaoY;
    private int tipo;
    private Paint paint;

    public Ponto(){
        this.id=0;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public int getNumPagina() {
        return numPagina;
    }

    public void setNumPagina(int numPagina) {
        this.numPagina = numPagina;
    }

    private int numPagina;

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public float getPossicaoX() {
        return possicaoX;
    }

    public void setPossicaoX(float possicaoX) {
        this.possicaoX = possicaoX;
    }

    public float getPossicaoY() {
        return possicaoY;
    }

    public void setPossicaoY(float possicaoY) {
        this.possicaoY = possicaoY;
    }





    public String getCaminhoLink() {

        return caminhoLink;
    }

    public void setCaminhoLink(String caminhoLink) {

        this.caminhoLink = caminhoLink;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }



}
