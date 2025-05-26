package com.spotlight;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    public void testCriacaoUsuario() {
        Usuario usuario = new Usuario("Luis Filipe", "luis@example.com", "senha123", "admin");
        assertNotNull(usuario.getId());
        assertEquals("Luis Filipe", usuario.getNome());
        assertEquals("luis@example.com", usuario.getEmail());
        assertEquals("senha123", usuario.getSenha());
        assertEquals("admin", usuario.getTipo());
    }

    @Test
    public void testToString() {
        Usuario usuario = new Usuario("Ana", "ana@example.com", "abc123", "funcionario");
        String texto = usuario.toString();
        assertTrue(texto.contains(usuario.getId()));
        assertTrue(texto.contains("Ana"));
        assertTrue(texto.contains("ana@example.com"));
        assertTrue(texto.contains("funcionario"));
    }
}
