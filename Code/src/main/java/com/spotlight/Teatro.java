package com.spotlight;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Teatro implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private String localizacao;
    private int capacidade;
    private List<Atividade> atividades;

    public Teatro(String nome, String localizacao, int capacidade) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.capacidade = capacidade;
        this.atividades = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public void listarAtividades() {
        if (atividades.isEmpty()) {
            System.out.println("Nenhuma atividade associada a este teatro.");
        } else {
            System.out.println("Atividades associadas:");
            for (Atividade atividade : atividades) {
                System.out.println("  - " + atividade);
            }
        }
    }

    @Override
    public String toString() {
        return "Teatro: " + nome + ", Localização: " + localizacao + ", Capacidade: " + capacidade;
    }
}