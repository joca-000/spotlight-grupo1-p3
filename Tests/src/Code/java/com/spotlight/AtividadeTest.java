package com.spotlight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AtividadeTest {

    private Atividade atividade;

    @BeforeEach
    void setUp() {
        atividade = new Atividade("Ensaio Geral", "2025-06-10", "Funcionario", "João", "Maria");
    }

    @Test
    void testInicializacaoCorreta() {
        assertEquals("Ensaio Geral", atividade.getNome());
        assertEquals("2025-06-10", atividade.getData());
        assertEquals("Funcionario", atividade.getTipoResponsavel());
        assertEquals("João", atividade.getResponsavel());
        assertEquals("Maria", atividade.getAdmResponsavel());
        assertFalse(atividade.isConcluida());
        assertFalse(atividade.isRejeitada());
        assertEquals("", atividade.getFeedback());
    }

    @Test
    void testSetFeedback() {
        atividade.setFeedback("Ótimo trabalho");
        assertEquals("Ótimo trabalho", atividade.getFeedback());
    }

    @Test
    void testSetConcluida() {
        atividade.setConcluida(true);
        assertTrue(atividade.isConcluida());
    }

    @Test
    void testSetRejeitada() {
        atividade.setRejeitada(true);
        assertTrue(atividade.isRejeitada());
    }

    @Test
    void testSetResponsavel() {
        atividade.setResponsavel("Carla");
        assertEquals("Carla", atividade.getResponsavel());
    }

    @Test
    void testToStringBasico() {
        String result = atividade.toString();
        assertTrue(result.contains("Atividade: Ensaio Geral"));
        assertTrue(result.contains("Data: 2025-06-10"));
        assertTrue(result.contains("Tipo Responsável: Funcionario"));
        assertTrue(result.contains("Responsável: João"));
        assertTrue(result.contains("Concluída: Não"));
    }

    @Test
    void testToStringComFeedbackERejeitada() {
        atividade.setFeedback("Precisa melhorar");
        atividade.setRejeitada(true);
        String result = atividade.toString();
        assertTrue(result.contains("Rejeitada: Sim"));
        assertTrue(result.contains("Feedback: Precisa melhorar"));
    }
}
