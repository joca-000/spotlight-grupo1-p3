public class Tarefa {
    private String descricao;
    private String responsavel; // Nome do usuário responsável
    private boolean concluida;

    public Tarefa(String descricao, String responsavel) {
        this.descricao = descricao;
        this.responsavel = responsavel;
        this.concluida = false;
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
        return "Tarefa: " + descricao + ", Responsável: " + responsavel + ", Concluída: " + (concluida ? "Sim" : "Não");
    }
}