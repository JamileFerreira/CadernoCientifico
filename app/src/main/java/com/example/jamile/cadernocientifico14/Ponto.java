package com.example.jamile.cadernocientifico14;

import android.graphics.Paint;

/**
 * Created by Jamile on 19/07/2017.
 */

public class Ponto {

    private String caminhoLink;
    private int possicaoX;
    private int possicaoY;
    private int tipo;

    public Paint getForma() {
        return forma;
    }

    public void setForma(Paint forma) {
        this.forma = forma;
    }

    public int getPossicaoX() {
        return possicaoX;
    }

    public void setPossicaoX(int possicaoX) {
        this.possicaoX = possicaoX;
    }

    public int getPossicaoY() {
        return possicaoY;
    }

    public void setPossicaoY(int possicaoY) {
        this.possicaoY = possicaoY;
    }

    private Paint forma= new Paint();



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
