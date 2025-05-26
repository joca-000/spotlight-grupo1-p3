package com.spotlight;
import java.io.Serializable;

public class Atividade implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private String data;
    private String tipoResponsavel;
    private String responsavel;
    private String feedback;
    private boolean concluida;
    private boolean rejeitada;
    private String admResponsavel;

    public Atividade(String nome, String data, String tipoResponsavel, String responsavel, String admResponsavel) {
        this.nome = nome;
        this.data = data;
        this.tipoResponsavel = tipoResponsavel;
        this.responsavel = responsavel;
        this.feedback = "";
        this.concluida = false;
        this.rejeitada = false;
        this.admResponsavel = admResponsavel;
    }

    public String getNome() {
        return nome;
    }

    public String getData() {
        return data;
    }

    public String getTipoResponsavel() {
        return tipoResponsavel;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public String getFeedback() {
        return feedback;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public boolean isRejeitada() {
        return rejeitada;
    }

    public String getAdmResponsavel() {
        return admResponsavel;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public void setRejeitada(boolean rejeitada) {
        this.rejeitada = rejeitada;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    @Override
    public String toString() {
        return "Atividade: " + nome + ", Data: " + data + ", Tipo Responsável: " + tipoResponsavel + ", Responsável: " + responsavel +
                ", Concluída: " + (concluida ? "Sim" : "Não") + (rejeitada ? ", Rejeitada: Sim" : "") + (feedback.isEmpty() ? "" : ", Feedback: " + feedback);
    }
}