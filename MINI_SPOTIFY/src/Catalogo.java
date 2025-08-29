package com.minispotify;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Catalogo {
    private Set<Midia> midiasDisponiveis;

    public Catalogo() {
        this.midiasDisponiveis = new HashSet<>();
    }

    public void adicionarMidia(Midia midia) throws MidiaJaExisteException {
        if (!midiasDisponiveis.add(midia)) {
            throw new MidiaJaExisteException("Mídia \"" + midia.getTitulo() + "\" já existe no catálogo.");
        }
        System.out.println("Mídia \"" + midia.getTitulo() + "\" adicionada ao catálogo.");
    }

    public Midia buscarPorTitulo(String titulo) {
        for (Midia midia : midiasDisponiveis) {
            if (midia.getTitulo().equalsIgnoreCase(titulo)) {
                return midia;
            }
        }
        return null;
    }

    public List<Midia> buscarPorArtista(String artista) {
        return midiasDisponiveis.stream()
                .filter(m -> m.getArtista().equalsIgnoreCase(artista))
                .collect(Collectors.toList());
    }

    public List<Midia> buscarPorGenero(Genero genero) {
        return midiasDisponiveis.stream()
                .filter(m -> m.getGenero().equals(genero))
                .collect(Collectors.toList());
    }

    public void listarTodasMidias() {
        if (midiasDisponiveis.isEmpty()) {
            System.out.println("O catálogo está vazio.");
            return;
        }
        System.out.println("Mídias disponíveis no catálogo:");
        for (Midia midia : midiasDisponiveis) {
            System.out.println("  - " + midia.getInfo());
        }
    }
}

