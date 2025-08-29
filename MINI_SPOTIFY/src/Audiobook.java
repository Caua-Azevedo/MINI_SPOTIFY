package com.minispotify;

public class Audiobook extends Midia {
    private String narrador;
    private int capitulo;

    public Audiobook(String titulo, String artista, int duracao, Genero genero, String narrador, int capitulo) {
        super(titulo, artista, duracao, genero);
        this.narrador = narrador;
        this.capitulo = capitulo;
    }

    public String getNarrador() {
        return narrador;
    }

    public int getCapitulo() {
        return capitulo;
    }

    @Override
    public void tocar() {
        System.out.println("Tocando audiobook: \"" + getTitulo() + "\" - Capítulo " + capitulo + " narrado por " + narrador);
    }

    @Override
    public String getInfo() {
        return super.getInfo() + ", Narrador: " + narrador + ", Capítulo: " + capitulo;
    }
}

