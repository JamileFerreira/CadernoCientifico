package com.example.jamile.cadernocientifico14;

/**
 * Created by Jamile on 19/07/2017.
 */

public class Ponto {

    private String caminhoLink;
    private int possicao;
    private int tipo;

    //Dimenção
    public int getPossicao() {
        return possicao;
    }

    public void setPossicao(int possicao) {
        this.possicao = possicao;
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
