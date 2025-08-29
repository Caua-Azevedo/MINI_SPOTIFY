package com.minispotify;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Catalogo catalogo = new Catalogo();
        Usuario usuario1 = new Usuario("Caua", "caua@email.com");
        Usuario usuario2 = new Usuario("Louhan", "louhan@email.com");

        try {
            Musica musica1 = new Musica("Back to Black", "Amy Whitehouse", 354, Genero.ROCK);
            Musica musica2 = new Musica("Rollin", "Limp Bizkit", 390, Genero.ROCK);
            Musica musica3 = new Musica("Billie Jean", "Michael Jackson", 294, Genero.POP);
            Podcast podcast1 = new Podcast("Flow Podcast", "Igor 3K", 7200, Genero.OUTRO, "Episódio 100", "Igor 3K");
            Audiobook audiobook1 = new Audiobook("Sapiens", "Yuval Noah Harari", 36000, Genero.OUTRO, "Harari", 1);

            catalogo.adicionarMidia(musica1);
            catalogo.adicionarMidia(musica2);
            catalogo.adicionarMidia(musica3);
            catalogo.adicionarMidia(podcast1);
            catalogo.adicionarMidia(audiobook1);

            catalogo.adicionarMidia(new Musica("Back to Black", "Amy Whitehouse", 354, Genero.ROCK));
        } catch (MidiaJaExisteException e) {
            System.out.println("Erro ao adicionar mídia: " + e.getMessage());
        }

        System.out.println("\n--- Catálogo de Mídias ---");
        catalogo.listarTodasMidias();

        try {
            usuario1.criarPlaylist("Minhas Favoritas");
            usuario1.criarPlaylist("Rock Clássico");
            usuario2.criarPlaylist("Estudos");

            usuario1.criarPlaylist("Minhas Favoritas");
        } catch (PlaylistJaExisteException e) {
            System.out.println("Erro ao criar playlist: " + e.getMessage());
        }

        try {
            Midia m1 = catalogo.buscarPorTitulo("Back to Black");
            if (m1 != null) usuario1.adicionarMidiaNaPlaylist("Minhas Favoritas", m1);

            Midia m2 = catalogo.buscarPorTitulo("Billie Jean");
            if (m2 != null) usuario1.adicionarMidiaNaPlaylist("Minhas Favoritas", m2);

            Midia m3 = catalogo.buscarPorTitulo("Back to Black");
            if (m3 != null) usuario1.adicionarMidiaNaPlaylist("Rock Clássico", m3);

            Midia m4 = catalogo.buscarPorTitulo("Rollin");
            if (m4 != null) usuario1.adicionarMidiaNaPlaylist("Rock Clássico", m4);

            Midia m5 = catalogo.buscarPorTitulo("Flow Podcast");
            if (m5 != null) usuario2.adicionarMidiaNaPlaylist("Estudos", m5);

            Midia m6 = catalogo.buscarPorTitulo("Sapiens");
            if (m6 != null) usuario2.adicionarMidiaNaPlaylist("Estudos", m6);

            Midia m7 = catalogo.buscarPorTitulo("Música Inexistente"); 
            if (m7 != null) {
                usuario1.adicionarMidiaNaPlaylist("Inexistente", m7);
            } else {
                System.out.println("Mídia 'Música Inexistente' não encontrada no catálogo. Não pode ser adicionada.");
            }

        } catch (PlaylistNaoEncontradaException e) {
            System.out.println("Erro ao adicionar mídia à playlist: " + e.getMessage());
        }

        System.out.println("\n--- Playlists de Caua ---");
        usuario1.visualizarPlaylists();

        System.out.println("\n--- Playlists de Louhan ---");
        usuario2.visualizarPlaylists();

        try {
            Midia mRemover1 = catalogo.buscarPorTitulo("Billie Jean");
            if (mRemover1 != null) {
                usuario1.removerMidiaDaPlaylist("Minhas Favoritas", mRemover1);
            } else {
                System.out.println("Mídia 'Billie Jean' não encontrada no catálogo. Não pode ser removida.");
            }

            Midia mRemover2 = catalogo.buscarPorTitulo("Música Inexistente");
            if (mRemover2 != null) {
                usuario1.removerMidiaDaPlaylist("Minhas Favoritas", mRemover2);
            } else {
                System.out.println("Mídia 'Música Inexistente' não encontrada no catálogo. Não pode ser removida.");
            }

        } catch (PlaylistNaoEncontradaException | MidiaNaoEncontradaException e) {
            System.out.println("Erro ao remover mídia da playlist: " + e.getMessage());
        }

        System.out.println("\n--- Playlists de Caua (Após remoção) ---");
        usuario1.visualizarPlaylists();

        System.out.println("\n--- Buscas no Catálogo ---");
        Midia buscaTitulo = catalogo.buscarPorTitulo("Rollin");
        if (buscaTitulo != null) {
            System.out.println("Busca por Título 'Rollin': " + buscaTitulo.getInfo());
        } else {
            System.out.println("Mídia 'Rollin' não encontrada.");
        }

        List<Midia> buscaArtista = catalogo.buscarPorArtista("Amy Whitehouse");
        System.out.println("Busca por Artista 'Amy Whitehouse':");
        if (buscaArtista.isEmpty()) {
            System.out.println("  Nenhuma mídia encontrada para 'Amy Whitehouse'.");
        } else {
            for (Midia midia : buscaArtista) {
                System.out.println("  - " + midia.getInfo());
            }
        }

        List<Midia> buscaGenero = catalogo.buscarPorGenero(Genero.ROCK);
        System.out.println("Busca por Gênero 'ROCK':");
        if (buscaGenero.isEmpty()) {
            System.out.println("  Nenhuma mídia encontrada para 'ROCK'.");
        } else {
            for (Midia midia : buscaGenero) {
                System.out.println("  - " + midia.getInfo());
            }
        }

        System.out.println("\n--- Testando Polimorfismo ---");
        Musica musicaTeste = new Musica("Imagine", "John Lennon", 183, Genero.POP);
        Podcast podcastTeste = new Podcast("Nerdcast", "Jovem Nerd", 5400, Genero.OUTRO, "Episódio Especial", "Alexandre Ottoni");
        Audiobook audiobookTeste = new Audiobook("1984", "George Orwell", 25200, Genero.OUTRO, "Narrador Desconhecido", 5);

        musicaTeste.tocar();
        podcastTeste.tocar();
        audiobookTeste.tocar();

        Playlist rockClassico = usuario1.getPlaylist("Rock Clássico");
        if (rockClassico != null) {
            System.out.println("\nDuracao total da playlist 'Rock Clássico': " + rockClassico.calcularDuracaoTotal() + " segundos.");
        }
    }
}

