package com.spotlight;
public class Funcionario {
    private String nome;
    private String endereco;
    private String dataNascimento;
    private String rg;
    private String cpf;

    public Funcionario(String nome, String endereco, String dataNascimento, String rg, String cpf) {
        this.nome = nome;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.rg = rg;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getRg() {
        return rg;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String toString() {
        return nome + " (CPF: " + cpf + ")";
    }
}
