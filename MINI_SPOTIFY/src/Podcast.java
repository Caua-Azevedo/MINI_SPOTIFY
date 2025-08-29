package com.minispotify;

public class Podcast extends Midia {
    private String episodio;
    private String apresentador;

    public Podcast(String titulo, String artista, int duracao, Genero genero, String episodio, String apresentador) {
        super(titulo, artista, duracao, genero);
        this.episodio = episodio;
        this.apresentador = apresentador;
    }

    public String getEpisodio() {
        return episodio;
    }

    public String getApresentador() {
        return apresentador;
    }

    @Override
    public void tocar() {
        System.out.println("Tocando podcast: \"" + getTitulo() + "\" - Episódio: " + episodio + " com " + apresentador);
    }

    @Override
    public String getInfo() {
        return super.getInfo() + ", Episódio: " + episodio + ", Apresentador: " + apresentador;
    }
}

