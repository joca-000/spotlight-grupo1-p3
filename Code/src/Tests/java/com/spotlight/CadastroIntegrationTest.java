package com.spotlight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class CadastroIntegrationTest {
    private Cadastro cadastro;
    private Login login;
    private static final String USUARIOS_FILE = "../usuarios.dat";

    @BeforeEach
    public void setUp() {
        File file = new File(USUARIOS_FILE);
        if (file.exists()) {
            file.delete();
        }
        login = new Login();
        cadastro = new Cadastro(login);
    }

    @Test
    public void testCadastrarNovoUsuario() {
        boolean result = cadastro.cadastrarUsuario("João", "joao@email.com", "senha123", "alta_administracao");
        assertTrue(result);

        Usuario usuario = login.buscarUsuarioPorNome("João");
        assertNotNull(usuario);
        assertEquals("João", usuario.getNome());
        assertEquals("joao@email.com", usuario.getEmail());
        assertEquals("alta_administracao", usuario.getTipo());
    }

    @Test
    public void testCadastrarUsuarioExistente() {
        cadastro.cadastrarUsuario("João", "joao@email.com", "senha123", "alta_administracao");
        boolean result = cadastro.cadastrarUsuario("João", "outro@email.com", "outrasenha", "backstage");
        assertFalse(result);

        Usuario usuario = login.buscarUsuarioPorNome("João");
        assertNotNull(usuario);
        assertEquals("joao@email.com", usuario.getEmail());
    }

    @Test
    public void testCadastrarUsuarioTipoInvalido() {
        boolean result = cadastro.cadastrarUsuario("Maria", "maria@email.com", "senha456", "tipo_invalido");
        assertFalse(result);

        Usuario usuario = login.buscarUsuarioPorNome("Maria");
        assertNull(usuario);
    }
}