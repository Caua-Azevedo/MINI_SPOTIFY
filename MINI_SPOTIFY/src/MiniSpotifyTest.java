package com.minispotify;

import java.util.List;

public class MiniSpotifyTest {

    public static void main(String[] args) {
        testAll();
    }

    public static void testAll() {
        System.out.println("\n--- Iniciando Testes do Mini Spotify ---");

        testMidiaCreationAndInfo();
        testCatalogoOperations();
        testUsuarioAndPlaylistCreation();
        testAddRemoveMidiaFromPlaylist();
        testPlaylistDuration();
        testSearchFunctions();
        testPolymorphism();
        testExceptionHandling();

        System.out.println("\n--- Testes do Mini Spotify Concluídos ---");
    }

    private static void testMidiaCreationAndInfo() {
        System.out.println("\nTeste: Criação de Mídias e Informações");
        Musica m1 = new Musica("Test Music", "Test Artist", 180, Genero.POP);
        Podcast p1 = new Podcast("Test Podcast", "Test Host", 600, Genero.OUTRO, "Ep1", "Host");
        Audiobook a1 = new Audiobook("Test Audiobook", "Test Author", 3600, Genero.OUTRO, "Narrator", 1);

        assert m1.getTitulo().equals("Test Music") : "Erro: Título da Música";
        assert p1.getEpisodio().equals("Ep1") : "Erro: Episódio do Podcast";
        assert a1.getNarrador().equals("Narrator") : "Erro: Narrador do Audiobook";

        System.out.println("  - Info Música: " + m1.getInfo());
        System.out.println("  - Info Podcast: " + p1.getInfo());
        System.out.println("  - Info Audiobook: " + a1.getInfo());
        System.out.println("Teste de Criação de Mídias e Informações: OK");
    }

    private static void testCatalogoOperations() {
        System.out.println("\nTeste: Operações do Catálogo");
        Catalogo catalogo = new Catalogo();
        Musica m1 = new Musica("Catalogo Musica 1", "Artista A", 200, Genero.ROCK);
        Musica m2 = new Musica("Catalogo Musica 2", "Artista B", 250, Genero.POP);

        try {
            catalogo.adicionarMidia(m1);
            catalogo.adicionarMidia(m2);
            assert catalogo.buscarPorTitulo("Catalogo Musica 1") != null : "Erro: Música não encontrada no catálogo";
            System.out.println("  - Mídias adicionadas ao catálogo.");
        } catch (MidiaJaExisteException e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }

        try {
            catalogo.adicionarMidia(m1);
            assert false : "Erro: MidiaJaExisteException não lançada para duplicata";
        } catch (MidiaJaExisteException e) {
            System.out.println("  - MidiaJaExisteException capturada com sucesso.");
        }
        System.out.println("Teste de Operações do Catálogo: OK");
    }

    private static void testUsuarioAndPlaylistCreation() {
        System.out.println("\nTeste: Criação de Usuário e Playlist");
        Usuario u1 = new Usuario("User Test", "test@email.com");
        assert u1.getNome().equals("User Test") : "Erro: Nome do Usuário";

        try {
            u1.criarPlaylist("Minha Playlist Teste");
            Playlist p = u1.getPlaylist("Minha Playlist Teste");
            assert p != null : "Erro: Playlist não criada";
            assert p.getNome().equals("Minha Playlist Teste") : "Erro: Nome da Playlist";
            System.out.println("  - Playlist criada com sucesso.");
        } catch (PlaylistJaExisteException e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }

        try {
            u1.criarPlaylist("Minha Playlist Teste");
            assert false : "Erro: PlaylistJaExisteException não lançada para duplicata";
        } catch (PlaylistJaExisteException e) {
            System.out.println("  - PlaylistJaExisteException capturada com sucesso.");
        }
        System.out.println("Teste de Criação de Usuário e Playlist: OK");
    }

    private static void testAddRemoveMidiaFromPlaylist() {
        System.out.println("\nTeste: Adicionar/Remover Mídia da Playlist");
        Usuario u1 = new Usuario("User AddRemove", "addremove@email.com");
        Catalogo catalogo = new Catalogo();
        Musica m1 = new Musica("AddRemove Musica 1", "Artista X", 100, Genero.POP);
        Musica m2 = new Musica("AddRemove Musica 2", "Artista Y", 120, Genero.ROCK);

        try {
            catalogo.adicionarMidia(m1);
            catalogo.adicionarMidia(m2);
            u1.criarPlaylist("Playlist AddRemove");

            u1.adicionarMidiaNaPlaylist("Playlist AddRemove", m1);
            u1.adicionarMidiaNaPlaylist("Playlist AddRemove", m2);
            assert u1.getPlaylist("Playlist AddRemove").getMidias().size() == 2 : "Erro: Mídias não adicionadas";
            System.out.println("  - Mídias adicionadas à playlist.");

            u1.removerMidiaDaPlaylist("Playlist AddRemove", m1);
            assert u1.getPlaylist("Playlist AddRemove").getMidias().size() == 1 : "Erro: Mídia não removida";
            System.out.println("  - Mídia removida da playlist.");

        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }

        try {
            u1.removerMidiaDaPlaylist("Playlist AddRemove", new Musica("Nao Existe", "", 0, Genero.OUTRO)); 
            assert false : "Erro: MidiaNaoEncontradaException não lançada ao remover inexistente";
        } catch (MidiaNaoEncontradaException e) {
            System.out.println("  - MidiaNaoEncontradaException capturada com sucesso ao remover.");
        } catch (PlaylistNaoEncontradaException e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
        System.out.println("Teste de Adicionar/Remover Mídia da Playlist: OK");
    }

    private static void testPlaylistDuration() {
        System.out.println("\nTeste: Duração da Playlist");
        Usuario u1 = new Usuario("User Duration", "duration@email.com");
        Catalogo catalogo = new Catalogo();
        Musica m1 = new Musica("Dur Musica 1", "", 60, Genero.POP);
        Musica m2 = new Musica("Dur Musica 2", "", 90, Genero.ROCK);

        try {
            catalogo.adicionarMidia(m1);
            catalogo.adicionarMidia(m2);
            u1.criarPlaylist("Playlist Duracao");
            u1.adicionarMidiaNaPlaylist("Playlist Duracao", m1);
            u1.adicionarMidiaNaPlaylist("Playlist Duracao", m2);

            int duracao = u1.getPlaylist("Playlist Duracao").calcularDuracaoTotal();
            assert duracao == 150 : "Erro: Duração total incorreta. Esperado 150, Obtido " + duracao;
            System.out.println("  - Duração da playlist calculada corretamente: " + duracao + "s.");
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
        System.out.println("Teste de Duração da Playlist: OK");
    }

    private static void testSearchFunctions() {
        System.out.println("\nTeste: Funções de Busca no Catálogo");
        Catalogo catalogo = new Catalogo();
        Musica m1 = new Musica("Search Musica 1", "Artista A", 100, Genero.POP);
        Musica m2 = new Musica("Search Musica 2", "Artista B", 120, Genero.ROCK);
        Musica m3 = new Musica("Search Musica 3", "Artista A", 150, Genero.POP);

        try {
            catalogo.adicionarMidia(m1);
            catalogo.adicionarMidia(m2);
            catalogo.adicionarMidia(m3);
        } catch (MidiaJaExisteException e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }

        Midia foundByTitle = catalogo.buscarPorTitulo("Search Musica 2");
        assert foundByTitle != null && foundByTitle.getArtista().equals("Artista B") : "Erro: Busca por título";
        System.out.println("  - Busca por título: OK");

        List<Midia> foundByArtist = catalogo.buscarPorArtista("Artista A");
        assert foundByArtist.size() == 2 : "Erro: Busca por artista";
        System.out.println("  - Busca por artista: OK");

        List<Midia> foundByGenre = catalogo.buscarPorGenero(Genero.ROCK);
        assert foundByGenre.size() == 1 : "Erro: Busca por gênero";
        System.out.println("  - Busca por gênero: OK");
        System.out.println("Teste de Funções de Busca no Catálogo: OK");
    }

    private static void testPolymorphism() {
        System.out.println("\nTeste: Polimorfismo (tocar mídias)");
        Midia m = new Musica("Poly Music", "Poly Artist", 100, Genero.POP);
        Midia p = new Podcast("Poly Podcast", "Poly Host", 200, Genero.OUTRO, "Ep Poly", "Poly Host");
        Midia a = new Audiobook("Poly Audiobook", "Poly Author", 300, Genero.OUTRO, "Poly Narrator", 1);

        System.out.print("  - "); m.tocar();
        System.out.print("  - "); p.tocar();
        System.out.print("  - "); a.tocar();
        System.out.println("Teste de Polimorfismo: OK (verificar saída do console)");
    }

    private static void testExceptionHandling() {
        System.out.println("\nTeste: Tratamento de Exceções");
        Catalogo catalogo = new Catalogo();
        Usuario u = new Usuario("User Ex", "ex@email.com");
        Musica m1 = new Musica("Ex Musica", "Ex Artist", 100, Genero.POP);

        try {
            catalogo.adicionarMidia(m1);
            catalogo.adicionarMidia(m1);
            assert false : "Erro: MidiaJaExisteException não lançada";
        } catch (MidiaJaExisteException e) {
            System.out.println("  - MidiaJaExisteException capturada: " + e.getMessage());
        }

        try {
            u.criarPlaylist("Ex Playlist");
            u.criarPlaylist("Ex Playlist");
            assert false : "Erro: PlaylistJaExisteException não lançada";
        } catch (PlaylistJaExisteException e) {
            System.out.println("  - PlaylistJaExisteException capturada: " + e.getMessage());
        }

        try {
            u.adicionarMidiaNaPlaylist("Playlist Inexistente", m1);
            assert false : "Erro: PlaylistNaoEncontradaException não lançada";
        } catch (PlaylistNaoEncontradaException e) {
            System.out.println("  - PlaylistNaoEncontradaException capturada: " + e.getMessage());
        }

        try {
            u.criarPlaylist("Playlist Para Remover");
            u.removerMidiaDaPlaylist("Playlist Para Remover", m1);
            assert false : "Erro: MidiaNaoEncontradaException não lançada ao remover de playlist vazia";
        } catch (MidiaNaoEncontradaException e) {
            System.out.println("  - MidiaNaoEncontradaException capturada ao remover de playlist: " + e.getMessage());
        } catch (PlaylistJaExisteException | PlaylistNaoEncontradaException e) { 
            System.out.println("Erro inesperado: " + e.getMessage());
        }
        System.out.println("Teste de Tratamento de Exceções: OK");
    }
}

