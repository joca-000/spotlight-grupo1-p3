import java.io.Serializable;
import java.util.UUID;

public class Tarefa implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String descricao;
    private String responsavel;
    private boolean concluida;

    public Tarefa(String descricao, String responsavel) {
        this.id = UUID.randomUUID().toString();
        this.descricao = descricao;
        this.responsavel = responsavel;
        this.concluida = false;
    }

    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void concluir() {
        this.concluida = true;
    }

    @Override
    public String toString() {
        return "Tarefa ID: " + id + ", Descrição: " + descricao + ", Responsável: " + responsavel + ", Concluída: " + (concluida ? "Sim" : "Não");
    }
}