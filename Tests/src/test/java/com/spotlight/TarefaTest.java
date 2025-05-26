package com.spotlight;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TarefaTest {

    @Test
    public void testCriacaoTarefa() {
        Tarefa tarefa = new Tarefa("Fazer relatório", "Carlos");
        assertNotNull(tarefa.getId());
        assertEquals("Fazer relatório", tarefa.getDescricao());
        assertEquals("Carlos", tarefa.getResponsavel());
        assertFalse(tarefa.isConcluida());
    }

    @Test
    public void testConcluirTarefa() {
        Tarefa tarefa = new Tarefa("Fazer relatório", "Carlos");
        tarefa.concluir();
        assertTrue(tarefa.isConcluida());
    }

    @Test
    public void testToString() {
        Tarefa tarefa = new Tarefa("Revisar documento", "Ana");
        String texto = tarefa.toString();
        assertTrue(texto.contains("Revisar documento"));
        assertTrue(texto.contains("Ana"));
        assertTrue(texto.contains("Concluída: Não"));
        tarefa.concluir();
        texto = tarefa.toString();
        assertTrue(texto.contains("Concluída: Sim"));
    }
}
