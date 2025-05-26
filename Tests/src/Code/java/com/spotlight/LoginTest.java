package com.spotlight;

import org.junit.jupiter.api.*;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    private Login login;

    @BeforeEach
    void setUp() {
        login = new Login();
        // Limpa a lista para garantir ambiente limpo
        login.getUsuarios().clear();
    }

    @AfterEach
    void tearDown() {
        // Apaga o arquivo de usuários criado durante o teste para não interferir depois
        File file = new File("usuarios.dat");
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testAdicionarEBuscarUsuario() {
        Usuario usuario = new Usuario("Luis", "luis@example.com", "senha123", "admin");
        login.adicionarUsuario(usuario);

        Usuario encontrado = login.buscarUsuarioPorNome("Luis");
        assertNotNull(encontrado);
        assertEquals("Luis", encontrado.getNome());
    }

    @Test
    void testAutenticarSucesso() {
        Usuario usuario = new Usuario("Ana", "ana@example.com", "1234", "user");
        login.adicionarUsuario(usuario);

        assertTrue(login.autenticar("Ana", "1234"));
        assertEquals(usuario, login.getUsuarioAutenticado());
    }

    @Test
    void testAutenticarFalha() {
        Usuario usuario = new Usuario("Pedro", "pedro@example.com", "abcd", "user");
        login.adicionarUsuario(usuario);

        assertFalse(login.autenticar("Pedro", "senhaErrada"));
        assertNull(login.getUsuarioAutenticado());
    }

    @Test
    void testLogout() {
        Usuario usuario = new Usuario("Maria", "maria@example.com", "senha", "admin");
        login.adicionarUsuario(usuario);
        login.autenticar("Maria", "senha");

        login.logout();
        assertNull(login.getUsuarioAutenticado());
    }

    @Test
    void testSalvarECarregarUsuarios() {
        Usuario usuario1 = new Usuario("Joao", "joao@example.com", "pass1", "user");
        Usuario usuario2 = new Usuario("Clara", "clara@example.com", "pass2", "admin");
        login.adicionarUsuario(usuario1);
        login.adicionarUsuario(usuario2);

        login.salvarUsuarios();

        // Cria nova instância para testar carregamento
        Login novoLogin = new Login();
        novoLogin.carregarUsuarios();

        List<Usuario> usuariosCarregados = novoLogin.getUsuarios();
        assertEquals(2, usuariosCarregados.size());
        assertTrue(usuariosCarregados.stream().anyMatch(u -> u.getNome().equals("Joao")));
        assertTrue(usuariosCarregados.stream().anyMatch(u -> u.getNome().equals("Clara")));
    }
}
