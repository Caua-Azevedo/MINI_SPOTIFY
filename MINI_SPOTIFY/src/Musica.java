package com.minispotify;

public class Musica extends Midia {
    public Musica(String titulo, String artista, int duracao, Genero genero) {
        super(titulo, artista, duracao, genero);
    }

    @Override
    public void tocar() {
        System.out.println("Tocando música: " + getTitulo() + " de " + getArtista());
    }
}

