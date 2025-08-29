package com.minispotify;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String nome;
    private List<Midia> midias;
    private Usuario usuarioDono;

    public Playlist(String nome, Usuario usuarioDono) {
        this.nome = nome;
        this.midias = new ArrayList<>();
        this.usuarioDono = usuarioDono;
    }

    public String getNome() {
        return nome;
    }

    public Usuario getUsuarioDono() {
        return usuarioDono;
    }

    public void adicionarMidia(Midia midia) {
        this.midias.add(midia);
    }

    public void removerMidia(Midia midia) throws MidiaNaoEncontradaException {
        if (!this.midias.remove(midia)) {
            throw new MidiaNaoEncontradaException("Mídia \'" + midia.getTitulo() + "\' não encontrada na playlist \'" + this.nome + "\'.");
        }
    }

    public int calcularDuracaoTotal() {
        int duracaoTotal = 0;
        for (Midia midia : midias) {
            duracaoTotal += midia.getDuracao();
        }
        return duracaoTotal;
    }

    public List<Midia> getMidias() {
        return new ArrayList<>(midias);
    }
}

