package com.spotlight;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TeatroTest {

    @Test
    public void testCriacaoTeatro() {
        Teatro teatro = new Teatro("Teatro Municipal", "Centro", 500);
        assertEquals("Teatro Municipal", teatro.getNome());
        assertEquals("Centro", teatro.getLocalizacao());
        assertEquals(500, teatro.getCapacidade());
        assertTrue(teatro.getAtividades().isEmpty());
    }

    @Test
    public void testSetters() {
        Teatro teatro = new Teatro("Nome1", "Local1", 100);
        teatro.setNome("Nome2");
        teatro.setLocalizacao("Local2");
        teatro.setCapacidade(200);
        assertEquals("Nome2", teatro.getNome());
        assertEquals("Local2", teatro.getLocalizacao());
        assertEquals(200, teatro.getCapacidade());
    }

    @Test
    public void testAdicionarEListarAtividades() {
        Teatro teatro = new Teatro("Teatro ABC", "Bairro", 300);
        Atividade atividade1 = new Atividade("Peça A", "2025-06-01", "Diretor", "João", "Admin1");
        Atividade atividade2 = new Atividade("Peça B", "2025-07-01", "Produtor", "Maria", "Admin2");

        teatro.getAtividades().add(atividade1);
        teatro.getAtividades().add(atividade2);

        assertEquals(2, teatro.getAtividades().size());
        assertTrue(teatro.getAtividades().contains(atividade1));
        assertTrue(teatro.getAtividades().contains(atividade2));
    }

    @Test
    public void testToString() {
        Teatro teatro = new Teatro("Teatro XYZ", "Zona Sul", 150);
        String texto = teatro.toString();
        assertTrue(texto.contains("Teatro XYZ"));
        assertTrue(texto.contains("Zona Sul"));
        assertTrue(texto.contains("150"));
    }
}
