public class Teatro {
    private String nome;
    private String localizacao;
    private int capacidade;
    private String descricao;

    public Teatro(String nome, String localizacao, int capacidade, String descricao) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.capacidade = capacidade;
        this.descricao = descricao;
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

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return nome + " - " + localizacao + " (Capacidade: " + capacidade + ")";
    }
}