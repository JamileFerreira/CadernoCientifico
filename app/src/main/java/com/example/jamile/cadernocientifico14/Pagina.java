package com.example.jamile.cadernocientifico14;

import java.util.Vector;

/**
 * Created by Jamile on 11/08/2017.
 */

public class Pagina {

    private String caminhoBackground;

    public int getNumPagina() {
        return numPagina;
    }

    public void setNumPagina(int numPagina) {
        this.numPagina = numPagina;
    }

    private int numPagina;
    Vector<Ponto> pontos = new Vector<Ponto>();

    public Vector<Ponto> getPontos() {
        return pontos;
    }

    public void setPontos(Vector<Ponto> pontos) {
        this.pontos = pontos;
    }

    public String getCaminhoBackground() {
        return caminhoBackground;
    }

    public void setCaminhoBackground(String caminhoBackground) {
        this.caminhoBackground = caminhoBackground;
    }
}
