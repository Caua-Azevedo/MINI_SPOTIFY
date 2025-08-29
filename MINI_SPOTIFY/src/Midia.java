package com.minispotify;

public abstract class Midia {
    private String titulo;
    private String artista;
    private int duracao;
    private Genero genero;

    public Midia(String titulo, String artista, int duracao, Genero genero) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracao = duracao;
        this.genero = genero;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public int getDuracao() {
        return duracao;
    }

    public Genero getGenero() {
        return genero;
    }

    public abstract void tocar();

    public String getInfo() {
        return "Título: " + titulo + ", Artista: " + artista + ", Duração: " + duracao + "s, Gênero: " + genero;
    }
}

