package com.spotlight;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioTest {
    @Test
    public void testFuncionarioCreationAndGetters() {
        Funcionario func = new Funcionario("Maria", "Rua 1", "01/01/1990", "123456", "98765432100");
        assertEquals("Maria", func.getNome());
        assertEquals("Rua 1", func.getEndereco());
        assertEquals("01/01/1990", func.getDataNascimento());
        assertEquals("123456", func.getRg());
        assertEquals("98765432100", func.getCpf());
    }

    @Test
    public void testFuncionarioToString() {
        Funcionario func = new Funcionario("Maria", "Rua 1", "01/01/1990", "123456", "98765432100");
        assertEquals("Maria (CPF: 98765432100)", func.toString());
    }
}