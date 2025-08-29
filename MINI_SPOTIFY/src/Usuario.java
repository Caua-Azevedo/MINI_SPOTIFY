package com.minispotify;

import java.util.HashMap;
import java.util.Map;

public class Usuario {
    private String nome;
    private String email;
    private Map<String, Playlist> playlists;

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.playlists = new HashMap<>();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void criarPlaylist(String nomePlaylist) throws PlaylistJaExisteException {
        if (playlists.containsKey(nomePlaylist)) {
            throw new PlaylistJaExisteException("Playlist com o nome '" + nomePlaylist + "' já existe.");
        }
        playlists.put(nomePlaylist, new Playlist(nomePlaylist, this));
        System.out.println("Playlist '" + nomePlaylist + "' criada com sucesso para o usuário " + nome + ".");
    }

    public void adicionarMidiaNaPlaylist(String nomePlaylist, Midia midia) throws PlaylistNaoEncontradaException {
        Playlist playlist = playlists.get(nomePlaylist);
        if (playlist == null) {
            throw new PlaylistNaoEncontradaException("Playlist '" + nomePlaylist + "' não encontrada.");
        }
        playlist.adicionarMidia(midia);
        System.out.println("Mídia '" + midia.getTitulo() + "' adicionada à playlist '" + nomePlaylist + "'.");
    }

    public void removerMidiaDaPlaylist(String nomePlaylist, Midia midia) throws PlaylistNaoEncontradaException, MidiaNaoEncontradaException {
        Playlist playlist = playlists.get(nomePlaylist);
        if (playlist == null) {
            throw new PlaylistNaoEncontradaException("Playlist '" + nomePlaylist + "' não encontrada.");
        }
        playlist.removerMidia(midia);
        System.out.println("Mídia '" + midia.getTitulo() + "' removida da playlist '" + nomePlaylist + "'.");
    }

    public Playlist getPlaylist(String nomePlaylist) {
        return playlists.get(nomePlaylist);
    }

    public void visualizarPlaylists() {
        if (playlists.isEmpty()) {
            System.out.println("O usuário " + nome + " não possui playlists.");
            return;
        }
        System.out.println("Playlists de " + nome + ":");
        for (Playlist playlist : playlists.values()) {
            System.out.println("  - " + playlist.getNome() + " (Duração Total: " + playlist.calcularDuracaoTotal() + "s)");
            if (playlist.getMidias().isEmpty()) {
                System.out.println("    (Playlist vazia)");
            } else {
                for (Midia midia : playlist.getMidias()) {
                    System.out.println("    -> " + midia.getInfo());
                }
            }
        }
    }
}

