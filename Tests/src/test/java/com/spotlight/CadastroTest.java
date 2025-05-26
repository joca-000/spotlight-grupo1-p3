package com.spotlight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CadastroTest {
    private Cadastro cadastro;
    private Login loginMock;

    @BeforeEach
    public void setUp() {
        loginMock = mock(Login.class);
        cadastro = new Cadastro(loginMock);
    }

    @Test
    public void testCadastrarUsuarioSucesso() {
        when(loginMock.buscarUsuarioPorNome("João")).thenReturn(null);
        boolean result = cadastro.cadastrarUsuario("João", "joao@email.com", "senha123", "alta_administracao");
        assertTrue(result);
        verify(loginMock).adicionarUsuario(any(Usuario.class));
    }

    @Test
    public void testCadastrarUsuarioExistente() {
        when(loginMock.buscarUsuarioPorNome("João")).thenReturn(new Usuario("João", "joao@email.com", "senha123", "alta_administracao"));
        boolean result = cadastro.cadastrarUsuario("João", "joao@email.com", "senha123", "alta_administracao");
        assertFalse(result);
        verify(loginMock, never()).adicionarUsuario(any(Usuario.class));
    }

    @Test
    public void testCadastrarUsuarioTipoInvalido() {
        when(loginMock.buscarUsuarioPorNome("João")).thenReturn(null);
        boolean result = cadastro.cadastrarUsuario("João", "joao@email.com", "senha123", "tipo_invalido");
        assertFalse(result);
        verify(loginMock, never()).adicionarUsuario(any(Usuario.class));
    }
}